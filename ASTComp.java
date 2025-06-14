public class ASTComp implements ASTNode {

    ASTNode left, right;
    String _op;

        public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
                        ASTType t1 = left.typecheck(e);
                        ASTType t2 = right.typecheck(e);
                        switch (_op) {
                                case "<":
                                case "<=":
                                case ">":
                                case ">=":
                                        if (t1 instanceof ASTTInt && t2 instanceof ASTTInt) {
                                                return new ASTTBool();
                                        }
                                        throw new TypeCheckerError("illegal types to " + _op + " operator");
                                case "==":
                                case "!=":
                                        if (t1 instanceof ASTTBool && t2 instanceof ASTTBool) {
                                                return new ASTTBool();
                                        } else if (t1 instanceof ASTTInt && t2 instanceof ASTTInt) {
                                                return new ASTTBool();
                                        }
                                        throw new TypeCheckerError("illegal types to " + _op + " operator");
                        }
                        throw new TypeCheckerError("illegal types to " + _op + " operator");
                }

        public IValue eval(Environment<IValue> e) throws InterpreterError {
                IValue v1 = left.eval(e);
                if (v1 instanceof VBool) {
                        IValue v2 = right.eval(e);
                        if (v2 instanceof VBool) {
                                boolean b1 = ((VBool) v1).getval();
                                boolean b2 = ((VBool) v2).getval();
                                if (_op.equals("==")) {
                                        return new VBool(b1 == b2);
                                } else {
                                        return new VBool(b1 != b2);
                                } 
                        }
                } else if (v1 instanceof VInt) {
                        IValue v2 = right.eval(e);
                        if(v2 instanceof VInt) {
                                int i1 = ((VInt) v1).getval();
                                int i2 = ((VInt) v2).getval();
                                switch (_op) {
                                        case "<":
                                                return new VBool(i1 < i2);
                                        case "<=":
                                                return new VBool(i1 <= i2);
                                        case ">":
                                                return new VBool(i1 > i2);
                                        case ">=":
                                                return new VBool(i1 >= i2);
                                        case "==":
                                                return new VBool(i1 == i2);
                                        default:
                                                return new VBool(i1 != i2);
                                }
                        }
                } 
                throw new InterpreterError("illegal types to " + _op + " operator");
        }

        public ASTComp(ASTNode l, String op, ASTNode r) {
                left = l;
                right = r;
                _op = op;
        }

}

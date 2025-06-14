public class ASTMult implements ASTNode {

        ASTNode left, right;

        public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
                ASTType t1 = left.typecheck(e);
                ASTType t2 = right.typecheck(e);
                if (t1 instanceof ASTTInt && t2 instanceof ASTTInt) {
                        return new ASTTInt();
                } else {
                        throw new TypeCheckerError("illegal types to * operator");
                }
        }

        public IValue eval(Environment<IValue> e) throws InterpreterError {
                IValue v1 = left.eval(e);
                IValue v2 = right.eval(e);
                if (v1 instanceof VInt && v2 instanceof VInt) {
                        int i1 = ((VInt) v1).getval();
                        int i2 = ((VInt) v2).getval();
                        return new VInt(i1 * i2);
                } else {
                        throw new InterpreterError("illegal types to * operator");
                }
        }

        public ASTMult(ASTNode l, ASTNode r) {
                left = l;
                right = r;
        }

}

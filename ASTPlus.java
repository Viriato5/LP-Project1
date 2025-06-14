public class ASTPlus implements ASTNode {

    ASTNode left, right;

    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
                ASTType t1 = left.typecheck(e);
                ASTType t2 = right.typecheck(e);
                // System.out.println("Type of + operator: " + t1.toStr() + " and " + t2.toStr());
                if (t1 instanceof ASTTString || t2 instanceof ASTTString) {
                        return new ASTTString();
                }
                if (t1 instanceof ASTTInt && t2 instanceof ASTTInt) {
                        return new ASTTInt();
                }
                throw new TypeCheckerError("illegal types to + operator");
        }

        public IValue eval(Environment<IValue> e) throws InterpreterError {
                IValue v1 = left.eval(e);
                IValue v2 = right.eval(e);
                if (v1 instanceof VString) {
                        String s1 = ((VString) v1).getval();
                        return new VString(s1.concat(v2.toStr()));
                }
                if (v2 instanceof VString) {
                        String s2 = ((VString) v2).getval();
                        return new VString(v1.toStr().concat(s2));
                }
                if (v1 instanceof VInt) {
                        if(v2 instanceof VInt) {
                                int i1 = ((VInt) v1).getval();
                                int i2 = ((VInt) v2).getval();
                                return new VInt(i1 + i2); 
                        }
                        
                } 
                throw new InterpreterError("Illegal types to + operator");
        }

        public ASTPlus(ASTNode l, ASTNode r) {
                left = l;
                right = r;
        }

}

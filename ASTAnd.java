public class ASTAnd implements ASTNode {

    ASTNode left, right;

        public IValue eval(Environment<IValue> e) throws InterpreterError {
                IValue v1 = left.eval(e);
                if (v1 instanceof VBool) {
                        IValue v2 = right.eval(e);
                        if(v2 instanceof VBool) {
                                boolean b1 = ((VBool) v1).getval();
                                boolean b2 = ((VBool) v2).getval();
                                return new VBool(b1 && b2);
                        }
                } 
                throw new InterpreterError("Illegal types to AND operator");
        }

        public ASTAnd(ASTNode l, ASTNode r) {
                left = l;
                right = r;
        }

}

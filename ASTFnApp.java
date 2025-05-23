public class ASTFnApp implements ASTNode {
        ASTNode arg;
        ASTNode left;

        public IValue eval(Environment<IValue> e) throws InterpreterError {
                IValue n = left.eval(e);
                if (n instanceof VClosure == false) {
                        throw new InterpreterError("Not a valid funciton: " + n);
                }
                VClosure closure = (VClosure) n;
                IValue v = arg.eval(e);
                Environment<IValue> newEnv = closure.getEnv().beginScope();
                newEnv.assoc(closure.getIdentifier(), v);
                return closure.getExp().eval(newEnv);
        }

        public ASTFnApp(ASTNode left, ASTNode arg) {
                this.left = left;
                this.arg = arg;
        }

}

public class ASTFnApp implements ASTNode {
        ASTNode arg;
        ASTNode left;

        public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
                ASTType functionType = left.typecheck(e);
                if (functionType instanceof ASTTId) {
                        ASTTId id = (ASTTId) functionType;
                        functionType = id.get(e);
                }
                if (!(functionType instanceof ASTTArrow)) {
                        throw new TypeCheckerError("Left side of application is not a function type: " + functionType.toStr());
                }
                ASTTArrow t1a = (ASTTArrow) functionType;
                ASTType t2 = arg.typecheck(e);
                
                if (!t1a.getArgType().isSubTypeOf(t2, e)) {
                        throw new TypeCheckerError("Illegal types to application operator: " + t1a.getArgType().toStr() + " and " + t2.toStr());
                }
                return t1a.getReturnType();
        }

        public IValue eval(Environment<IValue> e) throws InterpreterError {
                IValue n = left.eval(e);
                if (n instanceof VClosure == false) {
                        throw new InterpreterError("Not a valid funciton: " + n);
                }
                VClosure closure = (VClosure) n;
                IValue v = arg.eval(e);
                Environment<IValue> newEnv = closure.getEnv().beginScope();
                newEnv.assoc(closure.getIdentifier(), v);
                IValue ret = closure.getExp().eval(newEnv);
                newEnv.endScope();
                return ret;
        }

        public ASTFnApp(ASTNode left, ASTNode arg) {
                this.left = left;
                this.arg = arg;
        }

}

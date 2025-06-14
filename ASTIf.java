public class ASTIf implements ASTNode {
        ASTNode condition;
        ASTNode thenBranch;
        ASTNode elseBranch;

        public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
                ASTType condType = condition.typecheck(e);
                if (condType instanceof ASTTBool == false) {
                        throw new TypeCheckerError("Condition must be of type Bool, found: " + condType);
                }
                ASTType thenType = thenBranch.typecheck(e);
                ASTType elseType = elseBranch.typecheck(e);
                if (!(thenType.isSubTypeOf(elseType, e) || elseType.isSubTypeOf(thenType, e))) {
                        throw new TypeCheckerError("Branches must have the same type: " + thenType.toStr() + " vs " + elseType.toStr());
                }
                return thenType;
        }

        public IValue eval(Environment<IValue> e) throws InterpreterError {
                IValue cond = condition.eval(e);
                if (cond instanceof VBool == false) {
                        throw new InterpreterError("Not a valid condition: " + cond);
                }
                VBool b = (VBool) cond;
                if (b.getval()) {
                        return thenBranch.eval(e);
                } else {
                        if (elseBranch == null) { // isto n acontece
                                return new VInt(0);
                        } else {
                                return elseBranch.eval(e);
                        }
                }
        }

        public ASTIf(ASTNode condition, ASTNode thenBranch, ASTNode elseBranch) {
                this.condition = condition;
                this.thenBranch = thenBranch;
                this.elseBranch = elseBranch;
        }

}

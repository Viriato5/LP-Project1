public class ASTMatch implements ASTNode {
        ASTNode lv;
        ASTNode nilExpression;
        String valueIdentifier;
        String headIdentifier;
        ASTNode consExpression;

        public IValue eval(Environment<IValue> e) throws InterpreterError {
                IValue lvValue = lv.eval(e);
                if (lvValue instanceof VNil) {
                        return nilExpression.eval(e);
                } else if (lvValue instanceof VIcons) { // Lazy list
                        VIcons iconsValue = (VIcons) lvValue;
                        Environment<IValue> newEnv = e.beginScope();
                        newEnv.assoc(valueIdentifier, iconsValue.getValue().eval(iconsValue.getEnv()));
                        newEnv.assoc(headIdentifier, iconsValue.getHead().eval(iconsValue.getEnv()));
                        return consExpression.eval(newEnv);
                } else if (lvValue instanceof Vcons) { // Eager list
                        Vcons consValue = (Vcons) lvValue;
                        Environment<IValue> newEnv = e.beginScope();
                        newEnv.assoc(valueIdentifier, consValue.getValue());
                        newEnv.assoc(headIdentifier, consValue.getHead());
                        return consExpression.eval(newEnv);
                } else {
                        throw new InterpreterError("Pattern match failed");
                }
        }

        public ASTMatch(ASTNode lv, ASTNode nilExpression, String valueIdentifier, String headIdentifier, ASTNode consExpression) {
                this.lv = lv;
                this.nilExpression = nilExpression;
                this.valueIdentifier = valueIdentifier;
                this.headIdentifier = headIdentifier;
                this.consExpression = consExpression;
        }

}

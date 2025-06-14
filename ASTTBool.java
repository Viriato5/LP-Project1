class ASTTBool implements ASTType {
        
    public ASTTBool() {
    }
    public String toStr() {
        return "bool";
    }

    public boolean equals(Object o) {
        if (o instanceof ASTTBool) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isSubTypeOf(ASTType other, Environment<ASTType> env) throws TypeCheckerError {
        if (other instanceof ASTTId) {
            ASTTId otherId = (ASTTId) other;
            ASTType otherType = otherId.get(env);
            return this.isSubTypeOf(otherType, env);
        }
        return other instanceof ASTTBool;
    }
}
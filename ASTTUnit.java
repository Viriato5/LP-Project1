class ASTTUnit implements ASTType {
        
    public ASTTUnit() {
    }
    
    public String toStr() {
        return "()";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ASTTUnit;
    }

    @Override
    public boolean isSubTypeOf(ASTType other, Environment<ASTType> env) throws TypeCheckerError {
        if (other instanceof ASTTId) {
            ASTTId otherId = (ASTTId) other;
            ASTType otherType = otherId.get(env);
            return this.isSubTypeOf(otherType, env);
        }
        return other instanceof ASTTUnit;
    }
}
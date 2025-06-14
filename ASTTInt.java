public class ASTTInt implements ASTType {
    
    public ASTTInt() {}

    public String toStr() {
        return "int";
    }

    public boolean equals(Object o) {
        return o instanceof ASTTInt;
    }

    @Override
    public boolean isSubTypeOf(ASTType other, Environment<ASTType> env) throws TypeCheckerError {
        if (other instanceof ASTTId) {
            ASTTId otherId = (ASTTId) other;
            ASTType otherType = otherId.get(env);
            return this.isSubTypeOf(otherType, env);
        }
        return other instanceof ASTTInt;
    }
}



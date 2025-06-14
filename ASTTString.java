public class ASTTString implements ASTType {

    public ASTTString() {}

    public String toStr() {
        return "string";
    }

    public boolean equals(Object o) {
        return o instanceof ASTTString;
    }

    @Override
    public boolean isSubTypeOf(ASTType other, Environment<ASTType> env) throws TypeCheckerError {
        if (other instanceof ASTTId) {
            ASTTId otherId = (ASTTId) other;
            ASTType otherType = otherId.get(env);
            return this.isSubTypeOf(otherType, env);
        }
        return other instanceof ASTTString;
    }

}

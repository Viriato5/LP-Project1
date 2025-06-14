public interface ASTType  {
    String toStr();
    boolean isSubTypeOf(ASTType t, Environment<ASTType> env) throws TypeCheckerError;
}



public class ASTList implements ASTNode {
    ASTNode value;
    ASTNode head;

    public ASTList(ASTNode v, ASTNode h) {
        value = v;
        head = h;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        return new Vcons(value.eval(e), head.eval(e));
    }

    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
        ASTType valueType = value.typecheck(e);
        ASTType headType = head.typecheck(e);
        
        if (headType instanceof ASTTList) {
            ASTTList tailListType = (ASTTList) headType;
            if (!(tailListType.getElementType() instanceof ASTTUnit) &&
             (!(valueType.isSubTypeOf(tailListType.getElementType(), e) && tailListType.getElementType().isSubTypeOf(valueType, e)))) {
                throw new TypeCheckerError("Value type does not match tail element type");
            }
            return new ASTTList(valueType);
        } else {
            throw new TypeCheckerError("Head must be a list type");
        }
    }
   
}

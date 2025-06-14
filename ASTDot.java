public class ASTDot implements ASTNode {
    ASTNode struct;
    String field;

    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
        ASTType isStruct = struct.typecheck(e);
        if (isStruct instanceof ASTTId) {
            isStruct = ((ASTTId) isStruct).get(e);
        }
        if (!(isStruct instanceof ASTTStruct)) {
            throw new TypeCheckerError("Expected a Struct: " + isStruct.toStr());
        }
        ASTTStruct structType = (ASTTStruct) isStruct;
        if (!structType.hasField(field)) {
            throw new TypeCheckerError("Field '" + field + "' doesn't exist in the struct.");
        }
        return structType.getFieldType(field);
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue something = struct.eval(e);
        if (!(something instanceof VStruct)) {
            throw new InterpreterError("Expected a Struct: " + something.toStr());
        }
        VStruct structValue = (VStruct) something;
        return structValue.getField(field);
    }

    public ASTDot(ASTNode s, String f) {
        struct = s;
        field = f;
    }

}

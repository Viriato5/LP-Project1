class VIcons implements IValue {
    ASTNode value;
    ASTNode head;
    Environment<IValue> env;

    VIcons(ASTNode v, ASTNode h, Environment<IValue> e) {
        value = v;
        head = h;
        env = e;
    }

    public ASTNode getValue() {
        return value;
    }

    public ASTNode getHead() {
        return head;
    }

    public Environment<IValue> getEnv() {
        return env;
    }

    public String toStr() {
        return "LazyList";
    }

}
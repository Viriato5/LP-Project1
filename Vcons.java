class Vcons implements IValue {
    IValue value;
    IValue head;

    Vcons(IValue v, IValue h) {
        value = v;
        head = h;
    }

    public IValue getValue() {
        return value;
    }

    public IValue getHead() {
        return head;
    }
    
    public String toStr() {
        return "List: " + value.toStr();
    }

}
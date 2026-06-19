class DynamicArray {
        private int array[];
        private int length;
        private int capacity;

    public DynamicArray(int capacity) {
        this.capacity = capacity;
        this.length = 0;
        this.array = new int[capacity];
    }

    public int get(int i) {
        return this.array[i];
    }

    public void set(int i, int n) {
        this.array[i] = n;
    }

    public void pushback(int n) {
        if(this.length==this.capacity){
            this.resize();
        }

        this.array[this.length] = n;

        this.length++;
    }

    public int popback() {
        int lastElement = this.array[this.length -1];
        this.length--;
        return lastElement;
    }

    public void resize() {
        this.capacity *= 2;
        int[] newArray = new int[this.capacity];
        for (int i = 0; i < this.length; i++){
                newArray[i] = this.array[i];
        }

        this.array = newArray;
    }

    public int getSize() {
        return this.length;
    }

    public int getCapacity() {
        return this.capacity;
    }
}

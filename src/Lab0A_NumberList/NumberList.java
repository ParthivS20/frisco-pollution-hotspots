package Lab0A_NumberList;

class NumberList {
    private Integer[] list;
    private int size;

    NumberList() {
        list = new Integer[2];
        size = 0;
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        String x = "";
        for(int i = 0; i < size; i++) {
            x += list[i] + ( i < size - 1 ? ", " : "" );
        }

        return "[" + x + "]";
    }

    void add(int index, Integer i) {
        if(index < 0 || index > size) throw new IndexOutOfBoundsException();

        if(size + 1 > list.length) doubleCapacity();
        size++;

        for(int j = index; j < size; j++) {
            Integer temp = list[j];
            list[j] = i;
            i = temp;
        }
    }

    boolean add(Integer i) {
        add(size, i);
        return true;
    }

    Integer get(int index) {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException();

        return list[index];
    }

    Integer set(int index, Integer i) {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException();

        Integer temp = list[index];
        list[index] = i;
        return temp;
    }

    Integer remove(int index) {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException();

        Integer removed = list[index];

        if(index < size - 1) {
            for(int i = index + 1; i < size; i++) {
                list[i - 1] = list[i];
            }
        }
        list[size - 1] = null;

        size--;
        return removed;
    }

    private void doubleCapacity() {
        Integer[] temp = new Integer[list.length * 2];

        for(int i = 0; i < size; i++) {
            temp[i] = list[i];
        }

        list = temp;
    }
}

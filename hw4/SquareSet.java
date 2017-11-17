import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Represents a Set of Squares with a
 * backing array.
 *
 * @author schen475
 * @version 1.0
 */
public class SquareSet implements Set<Square> {

    private Square[] squares = new Square[10];

    /**
     * Creates a set of squares.
     *
     */
    public SquareSet() {

    }

    /**
     * Creates a set of squares with parameters.
     *
     * @param colSqua the Collection of Squares
     */
    public SquareSet(Collection<Square> colSqua) {
        addAll(colSqua);
    }

    @Override
    public boolean add(Square square) {
        String name = square.toString();
        if (name.length() == 2) {
            if (!('a' <= name.charAt(0) && name.charAt(0) <= 'h')) {
                throw new InvalidSquareException(name
                        + "is an invalid square.");
            }
            if (!('1' <= name.charAt(1) && name.charAt(1) <= '8')) {
                throw new InvalidSquareException(name
                        + "is an invalid square.");
            }
        } else {
            throw new InvalidSquareException(name
                    + "is an invalid square.");
        }
        if (square == null) {
            throw new NullPointerException("Cannot add nulls to set.");
        } else if (contains(square)) {
            return false;
        } else {
            if (size() >= squares.length) {
                Square[] temp = new Square[2 * size()];
                for (int i = 0; i < squares.length; i++) {
                    temp[i] = squares[i];
                }
                squares = temp;
            }
            squares[size()] = square;
            return true;
        }
    }

    @Override
    public boolean addAll(Collection<? extends Square> newSquares) {
        boolean valid = true;
        for (Square newSquare : newSquares) {
            String name = newSquare.toString();
            if (name.length() == 2) {
                if (!('a' <= name.charAt(0) && name.charAt(0) <= 'h')) {
                    valid = false;
                }
                if (!('1' <= name.charAt(1) && name.charAt(1) <= '8')) {
                    valid = false;
                }
            } else {
                valid = false;
            }
        }
        if (!valid) {
            throw new InvalidSquareException("At least one"
                    + "Square was invalid.");
        }
        boolean addedOne = false;
        for (Square newSquare : newSquares) {
            addedOne = add(newSquare) || addedOne;
        }
        return addedOne;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("'clear' method is"
                + " unsupported by this set");
    }

    @Override
    public boolean contains(Object o) {
        for (Square square : squares) {
            if (square != null) {
                if (square.equals(o)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> items) {
        boolean all = true;
        for (Object item : items) {
            if (!(contains(item))) {
                all = false;
            }
        }
        return all;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (o instanceof Set) {
            Set obj = (Set) o;
            return this.containsAll(obj)
                    && obj.containsAll(this)
                    && this.size() == obj.size();
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (Square square : squares) {
            if (square != null) {
                hash += square.hashCode();
            }
        }
        return hash;
    }

    @Override
    public boolean isEmpty() {
        if (size() != 0) {
            return false;
        }
        return true;
    }

    @Override
    public Iterator<Square> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Square> {
        private int pointer;

        public boolean hasNext() {
            return pointer < size();
        }

        public Square next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                return squares[pointer++];
            }

        }
    }

    @Override
    public boolean remove(Object o) {
        boolean altered = false;
        int index = -1;
        int finalIndex = -1;
        for (Square square : squares) {
            index++;
            if (square != null) {
                if (square.equals(o)) {
                    altered = true;
                    finalIndex = index;
                }
            }
        }
        if (altered) {
            Square[] temp = new Square[size()];
            for (int i = 0; i < finalIndex; i++) {
                temp[i] = squares[i];
            }
            for (int i = finalIndex + 1; i < size(); i++) {
                temp[i - 1] = squares[i];
            }
            temp[size() - 1] = null;
            squares = temp;
        }
        return altered;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("'removeAll' method"
                + "is unsupported by this set");
    }

    @Override
    public boolean retainAll(Collection<?> samples) {
        throw new UnsupportedOperationException("'retainAll' method"
                + "is unsupported by set");
    }

    @Override
    public int size() {
        int count = 0;
        for (Square square : squares) {
            if (square != null) {
                count++;
            }
        }
        if (count > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return count;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        for (int i = 0; i < size(); i++) {
            array[i] = squares[i];
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size()) {
            a = (T[]) Array.newInstance(a.getClass().getComponentType(),
                    size());
        } else if (a.length > size()) {
            a[size()] = null;
        }
        System.arraycopy(squares, 0, a, 0, size());
        return a;
    }
}
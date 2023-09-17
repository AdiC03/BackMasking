import java.util.EmptyStackException;

// this implementation works and used it on ciphered.wav

public class ArrayStack implements BKStack {
    private int curIndex; // Number of values
    private double [] valArray; // array storing the stack & 10 is initial capacity
    final int INITIAL_CAPACITY = 10;

    public ArrayStack () {
        valArray = new double[INITIAL_CAPACITY];
        curIndex = 0;
    }

    public boolean isEmpty()
    {
        return curIndex == 0;
    }

    public int count()
    {
        return curIndex;
    }

    public void push(double d)
    {
        if (curIndex >= valArray.length)
        {
            resize();
        }
        valArray[curIndex] = d;
        curIndex++;
    }

    public double pop()
    {
        if (isEmpty())
        {
            throw new EmptyStackException();
        }
        double curVal = valArray[curIndex - 1];
        curIndex--;
        return curVal;
    }

    public double peek()
    {
        if (isEmpty())
        {
            throw new EmptyStackException();
        }
        return valArray[curIndex - 1];
    }

    private void resize()
    {
        double [] temp = new double[valArray.length * 2];
        for (int i = 0; i < valArray.length; i++)
        {
            temp[i] = valArray[i];
        }
        valArray = temp;
    }

}

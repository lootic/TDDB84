package lab;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;

public class S_ShapeIterator extends AbstractIterator {
	private AbstractList<AbstractShape> list;
	private Iterator<AbstractShape> iterator;
	private AbstractShape current;

	S_ShapeIterator(AbstractSquare root) {
		this.list = root.getListOfShapes(new ArrayList<AbstractShape>());
		first();
	}

	@Override
	public Object currentItem() {
		return current;
	}

	@Override
	public void first() {
		this.iterator = list.iterator();
		next();
	}

	@Override
	public boolean isDone() {
		System.out.println(current == null);
		return current == null;
	}

	@Override
	public void next() {
		if (iterator.hasNext()) {
			current = iterator.next();
		} else {
			current = null;
		}
	}
}

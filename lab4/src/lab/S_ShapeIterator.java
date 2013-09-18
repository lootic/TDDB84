package lab;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;

public class S_ShapeIterator extends AbstractIterator {
	private AbstractList<AbstractShape> list;
	private Iterator<AbstractShape> iterator;
	private AbstractShape current;

	S_ShapeIterator(AbstractSquare root) {
		System.out.println("S_ShapeIterator Constructor ("+ root.toString()+")");
		this.list = root.getListOfShapes(new ArrayList<AbstractShape>());
		System.out.println(list.toString());
		first();
	}

	@Override
	public Object currentItem() {
		if (current == null)
			System.out.println("currentItem(), return: " + null);
		else
			System.out.println("currentItem(), return: " + current.toString());
		return current;
	}

	@Override
	public void first() {
		System.out.println("first()");
		this.iterator = list.iterator();
		next();
	}

	@Override
	public boolean isDone() {
		System.out.println("isDone(), return: " + current == null);
		return current == null;
	}

	@Override
	public void next() {
		System.out.println("next()");
		if (iterator.hasNext()) {
			current = iterator.next();
		} else {
			current = null;
		}
	}
}

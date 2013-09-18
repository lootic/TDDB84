package lab;

public class S_ShapeVisitor extends AbstractVisitor{

	@Override
	public void visit(Square s)
	{
		++numberOfVisits;
		s.paint(g);
	}

	@Override
	public void visit(Rectangle r) {
		++numberOfVisits;
		r.paint(g);
	}

	@Override
	public void visit(Circle c) {
		++numberOfVisits;
		c.paint(g);
	}

	@Override
	public void visit(Triangle t) {
		++numberOfVisits;
		t.paint(g);
	}
	
	public void visit(SquareProxy s) {
		++numberOfVisits;
		s.paint(g);
	}
	
}

package view.modules;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;



import de.htwg.se.dog.view.modules.OverlapLayout;

public class OverlapLayoutTest {
	OverlapLayout layout; 
	JLabel test,test1;
	JPanel panel;
	Container x;
	Dimension x1;
	Dimension x2;
	@Before
	public void setUp() {
		x2 = new Dimension(0,0);
		x1 = new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE);
		x = new Container();
		test = new JLabel();
		test1 = new JLabel();
		layout = new OverlapLayout();
		panel = new JPanel(layout);
		panel.add(test);
		panel.add(test1);
		layout.setPopupInsets(new Insets(0,0,0,0));
		layout.addLayoutComponent("bla", new JLabel());
	}

	@Test
	public void testOverlapPosition() {
		assertEquals(layout.getOverlapPosition(), new Point(0,0));
	}
	@Test
	public void testGetPopupInsets() {
		assertEquals(layout.getPopupInsets(), new Insets(0, 0, 0, 0));
	}
	@Test
	public void testLayoutAllignment() {
		Container x = new Container();
		layout.invalidateLayout(x);
		assertEquals(layout.getLayoutAlignmentX(x),  0.5f, 0.0002);
		assertEquals(layout.getLayoutAlignmentY(x),  0.5f, 0.0002);
	}
	@Test
	public void maximumLayoutSize() {
		assertEquals(layout.maximumLayoutSize(x), x1);
		assertEquals(layout.minimumLayoutSize(x), x2);
	}
	@Test
	public void testLayoutContainer() {
		layout.layoutContainer(x);
	}
	@Test
	public void testMoveUp() {
		assertNull(layout.getConstraints(test));
		layout.addLayoutComponent(test1, OverlapLayout.POPUP);
        layout.addLayoutComponent(test, OverlapLayout.POPUP);
        layout.addLayoutComponent(test, OverlapLayout.POPDOWN);
        panel.invalidate();
        panel.validate();
		assertTrue(layout.getConstraints(test1));
		layout.resetHighlighters();
		assertFalse(layout.getConstraints(test1));
	}
	@Test
	public void testPreferredLayoutSize() {
		assertEquals(x2, layout.preferredLayoutSize(panel));
	}
	@Test
	public void testRemoveComponent() {
		assertEquals(panel.getComponentCount(), 2);
		layout.removeLayoutComponent(test1);
		assertNull(layout.getConstraints(test1));
	}
}

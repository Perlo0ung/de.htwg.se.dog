package de.htwg.se.dog.view.modules;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The <code>OverlapLayout</code> class is a layout manager that lays out a
 * container's components in an overlapping fashion. A component can be painted
 * "above" A main usage for this layout might be in the creation of
 * "card games". A few features have been added that might be handy in these
 * cases:
 * 
 * a) a "popup" feature - when a component is selected in can "popup" from its
 * regular location so it visibly stands out. To accomplish this some extra
 * space must be reserved in the container for the popup. This is done by using
 * the setPopupInsets method which allow you to control the popup direction. In
 * addition you can add/remove a simple constraint to the component. POP_UP will
 * popup the component. POP_DOWN or null will paint the component in its regular
 * location. b) when a component is made "invisible" you can reserve its
 * location in the container so all the other components don't shift.
 * 
 * Note: this layout is achieved by changing the ZOrder of components in the
 * container. It will not work for all components as some compnents will always
 * paint themselves on the top of others. This seems to happen with components
 * like JButton as rollover effects are painted when a mouse moves over the
 * components.
 */
/**
 * @author Michael
 * 
 */
public class OverlapLayout implements LayoutManager2 {

	public static final Boolean POPUP = Boolean.TRUE;
	public static final Boolean POPDOWN = Boolean.FALSE;

	private static final float ALIGN = 0.5f;
	private static final int PREFERRED = 0;
	private static final int MINIMUM = 1;

	private Point overlapPosition;

	// Reserve extra space so a component can "popup"
	private Insets popupInsets = new Insets(0, 0, 0, 0);

	// Track original order in which the components where added
	private List<Component> components = new ArrayList<Component>();

	// Track a constraint added to a component
	private Map<Component, Boolean> constraints = new HashMap<Component, Boolean>();

	// Track maximum dimension of any component for easier layout
	private Dimension maximumSize = new Dimension();

	/**
	 * Convenience constructor to provide for "stacking" of components. Each
	 * component will be stacked above the previous component and sized to fill
	 * the space of the parent container.
	 */
	public OverlapLayout() {
		this(new Point(0, 0));
	}

	/**
	 * Convenience constructor. Each component will overlap above the previous
	 * component.
	 * 
	 * @param overlapPosition
	 *            a Point defining the relative amount of overlap
	 */
	public OverlapLayout(Point overlapPosition) {
		setOverlapPosition(overlapPosition);
	}

	/**
	 * Get the overlapping position of each component
	 * 
	 * @returns the Point representing the overlapped position
	 */
	public Point getOverlapPosition() {
		return overlapPosition;
	}

	/**
	 * Specify the position where the overlapped component should be painted.
	 * 
	 * @param overlapPosition
	 *            the position where the next component is painted
	 */
	private void setOverlapPosition(Point overlapPosition) {
		this.overlapPosition = overlapPosition;
	}

	/**
	 * Get the popup insets
	 * 
	 * @returns the popup insets
	 */
	public Insets getPopupInsets() {
		return popupInsets;
	}

	/**
	 * Define extra space to be reserved by the container. This will allow
	 * components to be "popped up" if required. Generally space would only be
	 * reserved on one side of the container.
	 * 
	 * @param popupInsets
	 *            Insets defining extra space for a particular side of the
	 *            container.
	 */
	public void setPopupInsets(Insets popupInsets) {
		this.popupInsets = popupInsets;
	}

	/**
	 * Gets the constraints for the specified component.
	 * 
	 * @param component
	 *            the component to be queried
	 * @return the constraint for the specified component, or null if component
	 *         is null or is not present in this layout
	 */
	public Boolean getConstraints(Component component) {
		return constraints.get(component);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.awt.LayoutManager2#addLayoutComponent(java.awt.Component,
	 *      java.lang.Object)
	 */
	@Override
	public void addLayoutComponent(Component component, Object constraint) {
		// Support simple Boolean constraint for painting a Component in
		// its "popped up" position

		if (constraint instanceof Boolean) {
			constraints.put(component, (Boolean) constraint);
		}

		// Keep a separate List of components in the order in which they where
		// added to the Container. This makes layout easier. First we need
		// to find the position the component was added at. We can't depend
		// on the component order in the parent Container as changing the
		// Z-Order also changes the order in the Container

		if (!components.contains(component)) {
			Container parent = component.getParent();
			int size = parent.getComponentCount();

			for (int i = 0; i < size; i++) {
				Component c = parent.getComponent(i);

				if (c.equals(component)) {
					components.add(i, component);

					// Need to change Z-Order so added components are painted
					// above the previously added component.

					parent.setComponentZOrder(component, size - i - 1);

					break;
				}
			}
		}
	}

	/**
	 * Removes the specified component from the layout.
	 * 
	 * @param comp
	 *            the component to be removed
	 */
	@Override
	public void removeLayoutComponent(Component component) {
		components.remove(component);
		constraints.remove(component);
	}

	/**
	 * Determine the minimum size on the Container
	 * 
	 * @param target
	 *            the container in which to do the layout
	 * @return the minimum dimensions needed to lay out the subcomponents of the
	 *         specified container
	 */
	@Override
	public Dimension minimumLayoutSize(Container parent) {
		synchronized (parent.getTreeLock()) {
			return getLayoutSize(parent, MINIMUM);
		}
	}

	/**
	 * Determine the preferred size on the Container
	 * 
	 * @param parent
	 *            the container in which to do the layout
	 * @return the preferred dimensions to lay out the subcomponents of the
	 *         specified container
	 */
	@Override
	public Dimension preferredLayoutSize(Container parent) {
		synchronized (parent.getTreeLock()) {
			return getLayoutSize(parent, PREFERRED);
		}
	}

	/**
	 * The calculation for minimum/preferred size it the same. The only
	 * difference is the need to use the minimum or preferred size of the
	 * component in the calculation.
	 * 
	 * @param parent
	 *            the container in which to do the layout
	 * 
	 * @param type
	 *            either MINIMUM or PREFERRED
	 */
	private Dimension getLayoutSize(Container parent, int type) {
		int width = 0;
		int height = 0;

		// All components will be resized to the maximum dimension

		for (Component component : components) {
			Dimension size = getDimension(component, type);
			width = Math.max(size.width, width);
			height = Math.max(size.height, height);
		}
		// Keep maximum dimension for easy access when laying out components

		if (type == PREFERRED) {
			maximumSize.width = width;
			maximumSize.height = height;
		}

		// Adjust size for each overlapping component

		width += Math.abs(overlapPosition.x);
		height += Math.abs(overlapPosition.y);

		// Adjust for parent Container and popup insets

		Insets parentInsets = parent.getInsets();
		width += parentInsets.left + parentInsets.right;
		height += parentInsets.top + parentInsets.bottom;

		width += popupInsets.left + popupInsets.right;
		height += popupInsets.top + popupInsets.bottom;

		return new Dimension(width, height);
	}

	private Dimension getDimension(Component component, int type) {
		switch (type) {
		case PREFERRED:
			return component.getPreferredSize();
		case MINIMUM:
			return component.getMinimumSize();
		default:
			return new Dimension(0, 0);
		}
	}

	/**
	 * Lays out the specified container using this layout.
	 * <p>
	 * 
	 * @param target
	 *            the container in which to do the layout
	 */
	@Override
	public void layoutContainer(Container parent) {
		synchronized (parent.getTreeLock()) {
			int size = components.size();
			// Determine location of first component

			Point location = new Point(0, 0);
			Insets parentInsets = parent.getInsets();

			// Layout right-to-left, else left-to-right

			overlapPos(location, parentInsets);
			// Set the size and location for each component

			for (int i = 0; i < size; i++) {
				Component component = components.get(i);

				component.setSize(maximumSize);

				// Set location of the component

				int x = location.x;
				int y = location.y;

				// Adjust location when component is "popped up"

				Boolean constraint = constraints.get(component);

				if (constraint != null && constraint == Boolean.TRUE) {
					x += popupInsets.right - popupInsets.left;
					y += popupInsets.bottom - popupInsets.top;
				}
				component.setLocation(x, y);

				// Calculate location of next component using the overlap
				// offsets

				location.x += overlapPosition.x;
				location.y += overlapPosition.y;

			}
		}
	}

	/**
	 * @param location
	 * @param parentInsets
	 */
	private void overlapPos(Point location, Insets parentInsets) {
		location.x = parentInsets.left + popupInsets.left;
		location.y = parentInsets.top + popupInsets.top;
	}

	/**
	 * There is no maximum.
	 */
	@Override
	public Dimension maximumLayoutSize(Container target) {
		return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

	/**
	 * Returns the alignment along the x axis. Use center alignment.
	 */
	@Override
	public float getLayoutAlignmentX(Container parent) {
		return ALIGN;
	}

	/**
	 * Returns the alignment along the y axis. Use center alignment.
	 */
	@Override
	public float getLayoutAlignmentY(Container parent) {
		return ALIGN;
	}

	/**
	 * Invalidates the layout, indicating that if the layout manager has cached
	 * information it should be discarded.
	 */
	@Override
	public void invalidateLayout(Container target) {
	}

	/**
	 * resets all highlighters
	 */
	public void resetHighlighters() {
		for (Entry<Component, Boolean> comp : constraints.entrySet()) {
			this.addLayoutComponent(comp.getKey(), POPDOWN);
		}
	}

	@Override
	public void addLayoutComponent(String arg0, Component arg1) {
		// TODO Auto-generated method stub

	}
}

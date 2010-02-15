
package com.google.code.linkedinapi.schema.dom;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.code.linkedinapi.schema.Position;
import com.google.code.linkedinapi.schema.ThreePastPositions;

public class ThreePastPositionsImpl
	extends BaseSchemaEntity
    implements ThreePastPositions
{

    private final static long serialVersionUID = 2461660169443089969L;
    protected List<Position> position;
    protected Long total;

    public List<Position> getPositionList() {
        if (position == null) {
            position = new ArrayList<Position>();
        }
        return this.position;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long value) {
        this.total = value;
    }

	@Override
	public void init(Element element) {
		setTotal(DomUtils.getAttributeValueAsLongFromNode(element, "total"));
		List<Element> positionElems = DomUtils.getChildElementsByLocalName(element, "position");
		for (Element positionElem : positionElems) {
			PositionImpl positionImpl = new PositionImpl();
			positionImpl.init(positionElem);
			getPositionList().add(positionImpl);
		}
	}

	@Override
	public Element toXml(Document document) {
		Element element = document.createElement("three-past-positions");
		DomUtils.setAttributeValueToNode(element, "total", getTotal());
		for (Position position : getPositionList()) {
			element.appendChild(((PositionImpl) position).toXml(document));
		}
		return element;
	}
}

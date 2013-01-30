package pt.ist.bennu.renderers.core.components;

public class HtmlTableHeaderRow extends HtmlTableRow {
	@Override
	public HtmlTableCell createCell() {
		HtmlTableCell cell = new HtmlTableCell(HtmlTableCell.CellType.HEADER);

		cell.setScope("col");

		addCell(cell);
		return cell;
	}
}

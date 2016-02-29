package com.agenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final AbonatRepository repo;

	private final AbonatEditor editor;

	private final Grid grid;

	private final TextField filter;

	private final Button addNewBtn;

	@Autowired
	public VaadinUI(AbonatRepository repo, AbonatEditor editor) {
		this.repo = repo;
		this.editor = editor;
		this.grid = new Grid();
		this.filter = new TextField();
		this.addNewBtn = new Button("New customer", FontAwesome.PLUS);
	}

	@Override
	protected void init(VaadinRequest request) {
		// build layout
		HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
		VerticalLayout mainLayout = new VerticalLayout(actions, grid, editor);
		actions.setWidthUndefined();
		// mainLayout.setSizeFull();

		setContent(mainLayout);

		// Configure layouts and components
		actions.setSpacing(true);
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		grid.setHeight(300, Unit.PIXELS);
		grid.setWidth(70, Unit.PERCENTAGE);
		grid.setColumns("nume", "prenume", "CNP", "nrTelFix", "nrTelMobil");

		filter.setInputPrompt("Filter by last name");

		// Hook logic to components

		// Replace listing with filtered content when user changes filter
		filter.addTextChangeListener(e -> listAbonat(e.getText()));

		// Connect selected Abonat to editor or hide if none is selected
		grid.addSelectionListener(e -> {
			if (e.getSelected().isEmpty()) {
				editor.setVisible(false);
			} else {
				Abonat a =(Abonat) e.getSelected().iterator().next();
				editor.editAbonat(a);
			}
		});

		// Instantiate and edit new Abonat the new button is clicked
		addNewBtn.addClickListener(e -> editor.editAbonat(new Abonat()));

		// Listen changes made by the editor, refresh data from backend
		editor.setChangeHandler(() -> {
			editor.setVisible(false);
			listAbonat(filter.getValue());
		});

		// Initialize listing
		listAbonat(null);
	}

	// tag::listAbonats[]
	private void listAbonat(String text) {
		if (StringUtils.isEmpty(text)) {
			grid.setContainerDataSource(new BeanItemContainer<Abonat>(Abonat.class, repo.findAll()));
		} else {
			grid.setContainerDataSource(
					new BeanItemContainer<Abonat>(Abonat.class, repo.findByNumeStartsWithIgnoreCase(text)));
		}
	}
	// end::listAbonats[]

}

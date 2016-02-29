package com.agenda;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringComponent
@UIScope
public class AbonatEditor extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final AbonatRepository repository;

	/**
	 * The currently edited abonat
	 */
	private Abonat abonat;

	/* Fields to edit properties in Abonat entity */
	TextField nume = new TextField("First name");
	TextField prenume = new TextField("Last name");
	TextField CNP = new TextField("CNP");
	TextField NrFix = new TextField("Nr Fix");
	TextField NrMobil = new TextField("Nr Mobil");

	/* Action buttons */
	Button save = new Button("Save", FontAwesome.SAVE);
	Button cancel = new Button("Cancel");
	Button delete = new Button("Delete", FontAwesome.TRASH_O);
	CssLayout actions = new CssLayout(save, cancel, delete);

	@Autowired
	public AbonatEditor(AbonatRepository repository) {
		this.repository = repository;

		addComponents(nume, prenume, CNP, NrFix, NrMobil, actions);

		// Configure and style components
		setSpacing(true);
		actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

		// wire action buttons to save, delete and reset
		save.addClickListener(e -> repository.save(abonat));
		delete.addClickListener(e -> repository.delete(abonat));
		cancel.addClickListener(e -> editAbonat(abonat));
		setVisible(false);
	}

	public interface ChangeHandler {

		void onChange();
	}

	public final void editAbonat(Abonat a) {
		final boolean persisted = a.getCNP() != null;
		if (persisted) {
			// Find fresh entity for editing
			abonat = repository.findOne(a.getCNP());
		} else {
			abonat = a;
		}
		cancel.setVisible(persisted);

		// Bind abonat properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving

		BeanFieldGroup.bindFieldsUnbuffered(abonat, this);
		NrFix.setValue(abonat.getNrTelFix().getNrTel());
		NrMobil.setValue(abonat.getNrTelMobil().getNrTel());

		setVisible(true);

		// A hack to ensure the whole form is visible
		save.focus();
		// Select all text in firstName field automatically
		nume.selectAll();
	}

	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		save.addClickListener(e -> h.onChange());
		delete.addClickListener(e -> h.onChange());
	}

}

package com.client.utils;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Consumer;

public abstract class UI {
	public static final MouseListener mouseEntered(Consumer<MouseEvent> event) {
		return new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				event.accept(e);
			}
		};
	}
	
	public static final FocusListener focusGained(Consumer<FocusEvent> event) {
		return new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				event.accept(e);
			}
		};
	}
	
	public static final FocusListener focusLost(Consumer<FocusEvent> event) {
		return new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				event.accept(e);
			}
		};
	}
}

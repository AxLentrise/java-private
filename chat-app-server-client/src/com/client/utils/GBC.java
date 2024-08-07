package com.client.utils;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GBC extends GridBagConstraints {

	public GBC(int gridx, int gridy, int anchor, int fill, Insets insets) {
		super(gridx, gridy, 1, 1, 0.0, 0.0, anchor, fill, insets, 0, 0);
		fill(fill);
		insets(insets);
	}

	public GBC(int gridx, int gridy, Insets insets) {
		this(gridx, gridy, CENTER, NONE, insets);
	}

	public GBC(int gridx, int gridy) {
		this(gridx, gridy, CENTER, NONE, null);
	}

	public GBC() {
		this(0, 0);
	}

	public GBC(GBC other) {
		this(other.gridx, other.gridy, (Insets) other.insets.clone());
		anchor = other.anchor;
		fill = other.fill;
		gridwidth = other.gridwidth;
		gridheight = other.gridheight;
		weightx = other.weightx;
		weighty = other.weighty;
	}

	private GBC fill(int _fill) {
		this.fill = _fill;

		if (fill == HORIZONTAL || fill == BOTH) {
			if (weightx == 0.0) {
				weightx = 1.0;
			}
		}

		if (fill == VERTICAL || fill == BOTH) {
			if (weighty == 0.0) {
				weighty = 1.0;
			}
		}

		return this;
	}

	public GBC none() {
		return fill(NONE);
	}

	public GBC both() {
		return fill(BOTH);
	}

	public GBC both(double wx, double wy) {
		if (wy < 0.0 || wx < 0.0) {
			throw new IllegalArgumentException("GBC: pesos devem ser maiores ou iguais a zero");
		}
		fill(BOTH);
		this.weightx = wx;
		this.weighty = wy;
		return this;
	}

	public GBC vertical() {
		return fill(VERTICAL);
	}

	public GBC vertical(double wy) {
		if (wy < 0.0) {
			throw new IllegalArgumentException("GBC: peso deve ser maior ou igual a zero");
		}
		fill(VERTICAL);
		this.weighty = wy;
		return this;
	}

	public GBC horizontal() {
		return fill(HORIZONTAL);
	}

	/**
	 * Define fill = HORIZONTAL.
	 * 
	 * @param wx - peso em X <b>(deve ser maior ou igual a zero)</b>
	 * @return o proprio elemento (para permitir concatenacaoo de operacoes)
	 */
	public GBC horizontal(double wx) {
		if (wx < 0.0) {
			throw new IllegalArgumentException("GBC: peso deve ser maior ou igual a zero");
		}
		fill(HORIZONTAL);
		weightx = wx;
		return this;
	}

	// /////////////////////////////////////////
	// /////////////////////////////////////////
	// ancoras

	/**
	 * Define ancora.
	 * 
	 * @param _anchor
	 * 
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	private GBC anchor(int _anchor) {
		this.anchor = _anchor;
		return this;
	}

	/**
	 * Define anchor = CENTER.
	 * 
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC center() {
		return anchor(CENTER);
	}

	/**
	 * Define anchor = NORTH.
	 * 
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC north() {
		return anchor(NORTH);
	}

	/**
	 * Define anchor = NORTHEAST.
	 * 
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC northeast() {
		return anchor(NORTHEAST);
	}

	/**
	 * Define anchor = EAST.
	 * 
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC east() {
		return anchor(EAST);
	}

	/**
	 * Define anchor = SOUTHEAST.
	 * 
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC southeast() {
		return anchor(SOUTHEAST);
	}

	/**
	 * Define anchor = SOUTH.
	 * 
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC south() {
		return anchor(SOUTH);
	}

	/**
	 * Define anchor = SOUTHWEST.
	 * 
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC southwest() {
		return anchor(SOUTHWEST);
	}

	/**
	 * Define anchor = WEST.
	 * 
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC west() {
		return anchor(WEST);
	}

	/**
	 * Define anchor = NORTHWEST.
	 * 
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC northwest() {
		return anchor(NORTHWEST);
	}

	/**
	 * Associa o peso default (1.0) a ambas as direcoes, definindo
	 * <code>fill == NONE</code>,
	 * 
	 * @see #pushxy(double, double)
	 * @see #none()
	 * 
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC pushxy() {
		return pushxy(1.0, 1.0);
	}

	/**
	 * Associa o peso default (1.0) direcao X, anulando a expansao nesta direcao.
	 * 
	 * @see #pushx(double)
	 * 
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC pushx() {
		return pushx(1.0);
	}

	/**
	 * Associa o peso default (1.0) direcao Y, anulando a expansso nesta direcao.
	 * 
	 * @see #pushy()
	 * 
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC pushy() {
		return pushy(1.0);
	}

	/**
	 * Associa pesos a ambas as direcoes, definindo <code>fill == NONE</code>.
	 * 
	 * @param wx - peso no eixo X
	 * @param wy - peso no eixo Y
	 * 
	 * @see #pushxy()
	 * @see #none()
	 * 
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC pushxy(double wx, double wy) {
		return none().weights(wx, wy);
	}

	/**
	 * Associa um peso a direcao X, anulando a expansao nesta direcao.
	 * 
	 * @param wx - peso X
	 * 
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC pushx(double wx) {
		switch (fill) {
		case BOTH:
			vertical();
			break;

		case HORIZONTAL:
			none();
			break;
		}
		return weightx(wx);
	}

	/**
	 * Associa um peso a direcao Y, anulando a expansao nesta direcao.
	 * 
	 * @param wy - peso Y
	 * 
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC pushy(double wy) {
		switch (fill) {
		case BOTH:
			horizontal();
			break;

		case VERTICAL:
			none();
			break;
		}
		return weighty(wy);
	}

	/**
	 * Faz com que o elemento aproveite espaco horizontal disponivel mas nao compita
	 * com os demais na distribuicao de espaco <code>(weightx == 0.0)</code>.
	 * <p>
	 * O fill horizontal ser� adicionado ao fill existente da seguinte forma:
	 * 
	 * <ul>
	 * <li>none --&gt; horizontal
	 * <li>horizontal --&gt; horizontal
	 * <li>vertical --&gt; both
	 * <li>both --&gt; both
	 * </ul>
	 * 
	 * @see #filly()
	 * @see #fillxy()
	 * 
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC fillx() {
		switch (fill) {
		case NONE:
			return horizontal(0.0);

		case HORIZONTAL:
		case BOTH:
			return weightx(0.0);

		case VERTICAL:
			return both(0.0, weighty);

		default:
			// nunca vai ser chamado, todos os casos estao cobertos
			return this;
		}
	}

	/**
	 * Faz com que o elemento aproveite espaco vertical disponivel mas nao computa
	 * com os demais na distribuicao de espaco <code>(weighty == 0.0)</code>.
	 * <p>
	 * O fill vertical sera adicionado ao fill existente da seguinte forma:
	 * 
	 * <ul>
	 * <li>none --&gt; vertical
	 * <li>horizontal --&gt; both
	 * <li>vertical --&gt; vertical
	 * <li>both --&gt; both
	 * </ul>
	 * 
	 * @see #fillx()
	 * @see #fillxy()
	 * 
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC filly() {
		switch (fill) {
		case NONE:
			return vertical(0.0);

		case VERTICAL:
		case BOTH:
			return weighty(0.0);

		case HORIZONTAL:
			return both(weightx, 0.0);

		default:
			// nunca vai ser chamado, todos os casos estao cobertos
			return this;
		}
	}

	/**
	 * Faz com que o elemento aproveite espaco disponivel em ambas as direcoes mas
	 * nao compita com os demais na distribuicao de espaco.
	 * 
	 * Equivale a <code>both(0.0, 0.0)</code>.
	 * 
	 * @see #fillx()
	 * @see #filly()
	 * 
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC fillxy() {
		return both(0.0, 0.0);
	}

	// /////////////////////////////////////////
	// /////////////////////////////////////////
	// insets

	/**
	 * Define insets [margem entre o componente e sua 'calula' no grid].
	 * 
	 * @param _insets - elemento do tipo {@link Insets}
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC insets(Insets _insets) {
		this.insets = _insets == null ? new Insets(0, 0, 0, 0) : _insets;
		return this;
	}

	/**
	 * Define insets [margem entre o componente e sua 'celula' no grid].
	 * 
	 * @param top
	 * @param left
	 * @param bottom
	 * @param right
	 * 
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC insets(int top, int left, int bottom, int right) {
		insets = new Insets(top, left, bottom, right);
		return this;
	}

	/**
	 * Define insets [margem entre o componente e sua 'celula' no grid]. O valor
	 * indicado e usado nos 4 cantos [top, left, bottom e right].
	 * 
	 * @param defaultValue - valor unico aplicado em top, left, bottom e right.
	 * 
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC insets(int defaultValue) {
		insets = new Insets(defaultValue, defaultValue, defaultValue, defaultValue);
		return this;
	}

	/**
	 * Define insets.top = top.
	 * 
	 * @param top
	 * 
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC top(int top) {
		insets.top = top;
		return this;
	}

	/**
	 * Define insets.bottom = bottom.
	 * 
	 * @param bottom
	 * 
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC bottom(int bottom) {
		insets.bottom = bottom;
		return this;
	}

	/**
	 * Define insets.right = right.
	 * 
	 * @param right
	 * 
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC right(int right) {
		insets.right = right;
		return this;
	}

	/**
	 * Define insets.left = left.
	 * 
	 * @param left
	 * 
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC left(int left) {
		insets.left = left;
		return this;
	}

	// /////////////////////////////////////////
	// /////////////////////////////////////////
	// pesos

	/**
	 * Define o peso na direcao horizontal.
	 * 
	 * @param wx - peso
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC weightx(double wx) {
		weightx = wx;
		return this;
	}

	/**
	 * Define o peso na direcao vertical.
	 * 
	 * @param wy - peso
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC weighty(double wy) {
		weighty = wy;
		return this;
	}

	/**
	 * Define os pesos nas direcoes horizontal e vertical.
	 * 
	 * @param wx - peso na direcao horizontal
	 * @param wy - peso na direcao vertical
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC weights(double wx, double wy) {
		weightx = wx;
		weighty = wy;
		return this;
	}

	// /////////////////////////////////////////
	// /////////////////////////////////////////
	// altura/largura [em celulas, nao pixels]

	/**
	 * Define a largura (<code>gridwidth</code>).
	 * 
	 * @param width
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC width(int width) {
		gridwidth = width;
		return this;
	}

	/**
	 * Define a altura (<code>gridheight</code>).
	 * 
	 * @param height
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC height(int height) {
		gridheight = height;
		return this;
	}

	/**
	 * Define a coordenada X no grid.
	 * 
	 * @param gx - coordenada X no grid
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC gridx(int gx) {
		gridx = gx;
		return this;
	}

	/**
	 * Define a coordenada Y no grid.
	 * 
	 * @param gy - coordenada Y no grid
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC gridy(int gy) {
		gridy = gy;
		return this;
	}

	/**
	 * Define quantas celulas o elemento ocupara na horizontal.
	 * 
	 * @param gw - numero de celulas ocupadas pelo componente na horizontal
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC gridwidth(int gw) {
		gridwidth = gw;
		return this;
	}

	/**
	 * Define quantas celulas o elemento ocupara na vertical.
	 * 
	 * @param gh - numero de celulas ocupadas pelo componente na vertical
	 * @return o proprio elemento (para permitir concatenacao de operacoes)
	 */
	public GBC gridheight(int gh) {
		gridheight = gh;
		return this;
	}

	// /////////////
	// outros

	/**
	 * Montagem de um texto depurativo com base em uma ancora.
	 * 
	 * @param value o valor de uma ancora.
	 * @return o texto associado.
	 */
	private static String getAnchorName(int value) {
		if (value == NORTHEAST) {
			return "NORTHEAST";
		}
		if (value == EAST) {
			return "EAST";
		}
		if (value == SOUTHEAST) {
			return "SOUTHEAST";
		}
		if (value == SOUTH) {
			return "SOUTH";
		}
		if (value == SOUTHWEST) {
			return "SOUTHWEST";
		}
		if (value == WEST) {
			return "WEST";
		}
		if (value == NORTHWEST) {
			return "NORTHWEST";
		}
		if (value == CENTER) {
			return "CENTER";
		}
		return Integer.toString(value);
	}

	/**
	 * Montagem de um texto depurativo com base em um tipos di preenchimento (fill).
	 * 
	 * @param value o valor de um fill.
	 * @return o texto associado.
	 */
	private static String getFillName(int value) {
		if (value == NONE) {
			return "NONE";
		}
		if (value == HORIZONTAL) {
			return "HORIZONTAL";
		}
		if (value == VERTICAL) {
			return "VERTICAL";
		}
		if (value == BOTH) {
			return "BOTH";
		}
		if (value == NORTH) {
			return "NORTH";
		}
		return Integer.toString(value);
	}
}
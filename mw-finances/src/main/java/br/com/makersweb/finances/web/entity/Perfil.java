/**
 * 
 */
package br.com.makersweb.finances.web.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 *
 * @author anderson.aristides
 *
 */
@Entity
@Table(name = "tb_perfil", uniqueConstraints = @UniqueConstraint(columnNames = { "nome" }))
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Perfil extends DefaultEntity implements Serializable {

	private static final long serialVersionUID = -980260915993656024L;

	@NotBlank(message = "{br.com.finances.text.campo.nome.obrigatorio}")
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_perfil_regra_acesso", joinColumns = {
			@JoinColumn(name = "regra_acesso_id") }, inverseJoinColumns = { @JoinColumn(name = "perfil_id") })
	private List<RegraAcesso> regraAcesso;

	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "perfils")
	private Set<Usuario> usuarios;

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the regraAcesso
	 */
	public List<RegraAcesso> getRegraAcesso() {
		return regraAcesso;
	}

	/**
	 * @param regraAcesso
	 *            the regraAcesso to set
	 */
	public void setRegraAcesso(List<RegraAcesso> regraAcesso) {
		this.regraAcesso = regraAcesso;
	}

	/**
	 * @return the usuarios
	 */
	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * @param usuarios
	 *            the usuarios to set
	 */
	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}

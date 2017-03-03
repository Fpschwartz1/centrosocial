package org.iaff.csiaff.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.iaff.csiaff.validation.AtributoConfirmacao;


@AtributoConfirmacao(atributo = "senha", atributoConfirmacao = "confirmacaoSenha", message = "Confirmação da senha não confere")
@Entity
@Table(name = "usuario")
@DynamicUpdate
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank(message = "Nome de usuário obrigatório")
	private String nome;

	@NotBlank(message = "E-mail obrigatório")
	@Email(message = "E-mail inválido")
	private String email;

	private String senha;

	@Transient
	private String confirmacaoSenha;
	
	private Boolean ativo;

	@Size(min = 1, message = "Selecione pelo menos um grupo")
	@ManyToMany
	@JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name = "codigo_usuario")
				, inverseJoinColumns = @JoinColumn(name = "codigo_grupo"))	
	private List<Grupo> grupos;

	// http://uaihebert.com/jpa-onetoone-unidirecional-e-bidirecional/
	// http://uaihebert.com/jpa-mini-livro-primeiros-passos-e-conceitos-detalhados/19/
	// http://forum.spring.io/forum/spring-projects/data/72442-jpa-optional-onetoone-not-optional
	// http://webdev.jhuep.com/~jcs/ejava-javaee/coursedocs/content/html/jpa-relationex-one2one.html
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_pessoa")
	private Pessoa pessoa;
	
	@PreUpdate
	private void preUpdate() {
		this.confirmacaoSenha = senha;
	}	

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}
	
	public boolean isNovo() {
		return codigo == null;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}

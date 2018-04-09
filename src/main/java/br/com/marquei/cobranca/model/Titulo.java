package br.com.marquei.cobranca.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import br.com.marquei.cobranca.dominio.EnumStatusTitulo;

@Entity
public class Titulo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotEmpty(message="Faça uma breve descrição do título")
	@Size(max = 60, message="A descrição pode ter até 60 caracteres.")
	private String descricao;
	
	@NotNull(message="Digite uma data de vencimento.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@NotNull(message="O Valor não pode ser nulo.")
	@NumberFormat(pattern = "#,###.00")
	@DecimalMin(value="0.01", message = "valor inválido.")
	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	private EnumStatusTitulo status;
	
	public boolean isSucesso() {
		return EnumStatusTitulo.RECEBIDO.equals(this.getStatus());
	}
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public EnumStatusTitulo getStatus() {
		return status;
	}
	public void setStatus(EnumStatusTitulo status) {
		this.status = status;
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
		Titulo other = (Titulo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	

}

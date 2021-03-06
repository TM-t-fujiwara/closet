package com.example.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "items")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id", nullable = false, precision = 11)
	private Integer itemId;
	
	/*CategoryID*/
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	/*subCategoryID*/
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "sub_category_id")
	private SubCategory subCategory;
	
	/*SeasonID*/
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "season_id")
	private Season season;
	
	/*ColorID*/
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color color;
	
	/*usersのID:双方向にするとき*/
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id")
	private User user;
	
	//@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@Column(name = "picture")
	private String picture;
	
	@Size(max = 300, message = "300字以下で入力してください")
	//@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@Column(name = "comment")
	private String comment;
	
	@JsonIgnore
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "created_at")
	private Date createdAt;
	
	@JsonIgnore
	@Column(name = "updated_at")
	private Date updatedAt;
	
	/*coordenateとの多対多連携*/
	@JsonIgnore
    @ManyToMany
    @JoinTable(name="coordinate", 
    	joinColumns = @JoinColumn( name = "item_id"),
        inverseJoinColumns = @JoinColumn(name="coordinate_id"))
    private List<Coordinate> coordinatelist;
	
	/*日付更新*/
    @PrePersist
    public void onPrePersist() {
        setCreatedAt(new Date());
        setUpdatedAt(new Date());
    }

    @PreUpdate
    public void onPreUpdate() {
        setUpdatedAt(new Date());
    }
}
package com.ezen.myproject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class boardVO 
{
	private int bno;
	private String title;
	private String writer;
	private String content;
	private String isDel;
	private String registerDate;
	private int read_count;
	private int cmt_count;
	private int file_count;
	
}

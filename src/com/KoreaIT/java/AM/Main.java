package com.KoreaIT.java.AM;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);
		Date today = new Date();
		SimpleDateFormat format;
		format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		int lastArticleId = 0;
		int hit = 0;
		/* a는 자른 문자열에서의 번호 */
		int a = 0;
		ArrayList<Article> articles = new ArrayList<>();
		while (true) {
			System.out.print("명령어 > ");
			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (command.equals("exit")) {
				break;
			}

			if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다");
					continue;
				}
				System.out.println("번호   /   제목");
				for (int i = articles.size(); i > 0; i--) {
					Article article = articles.get(i - 1);
					System.out.printf("%d	/	%s\n", article.id, article.title);
				}
			} else if (command.equals("article write")) {
				int id = lastArticleId + 1;
				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String body = sc.nextLine();
				Date date = new Date();

//				System.out.println(title + " / " + body);

				System.out.printf("%d번글이 생성되었습니다\n", id);
				lastArticleId++;

				articles.add(new Article(id, title, body, date, hit));

			} else if (command.startsWith("article detail")) {

				try {
					String[] n = command.split(" ");
					a = Integer.parseInt(n[2]);
					Article article = articles.get(a - 1);
					System.out.println("번호: " + article.id);
					System.out.println("날짜: " + format.format(article.date));
					System.out.println("제목: " + article.title);
					System.out.println("내용: " + article.body);
					article.hit++;
					System.out.println("조회수: " + article.hit);
			/* 다중 예외 처리를 하려면 상위 예외 클래스가 하위 예외 클래스보다 아래에 위치해야 한다.*/
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("명령어를 다시 입력해주세요.");
				} catch (IndexOutOfBoundsException e) {
					System.out.println(a + "번 게시물은 존재하지 않습니다.");
				}

			} else if (command.startsWith("article delete")) {
				
				try {
					String[] n = command.split(" ");
					a = Integer.parseInt(n[2]);
					articles.remove(a - 1);
					System.out.println(a + "번 게시물이 삭제 되었습니다.");

				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("명령어를 다시 입력해주세요.");
				} catch (IndexOutOfBoundsException e) {
					System.out.println(a + "번 게시물은 존재하지 않습니다.");
				}
			}

			else {
				System.out.println("존재하지 않는 명령어입니다");
			}
		}

		System.out.println("==프로그램 끝==");

		sc.close();
	}
}

class Article {
	int id;
	String title;
	String body;
	Date date;
	int hit;
	public Article(int id, String title, String body, Date date) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.date = date;
	}
	public Article(int id, String title, String body, Date date, int hit) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.date = date;
		this.hit = hit;
	}
}
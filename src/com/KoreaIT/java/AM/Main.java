package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
	static int num = 1;
	static ArrayList<Article> articles = new ArrayList<>();
	public static void testData() {
		
		String today = Util.getNowDateTimeStr();
		articles.add(new Article(1, "test1", "test1", today, 0));

	}
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");
		testData();
		Scanner sc = new Scanner(System.in);
		
		int lastArticleId = 0;
		int hit = 0;
		/* a는 자른 문자열에서의 번호 */
		int a = 0;
		
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
				int id = lastArticleId + 1 + num;
				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String body = sc.nextLine();
				String regDate = Util.getNowDateTimeStr();

//				System.out.println(title + " / " + body);

				System.out.printf("%d번글이 생성되었습니다\n", id);
				lastArticleId++;

				articles.add(new Article(id, title, body, regDate, hit));

			} else if (command.startsWith("article detail")) {

				try {
					String[] n = command.split(" ");
					a = Integer.parseInt(n[2]);
					Article article = articles.get(a - 1);
					System.out.println("번호: " + article.id);
					System.out.println("날짜: " + article.regDate);
					System.out.println("제목: " + article.title);
					System.out.println("내용: " + article.body);
					article.hit++;
					System.out.println("조회수: " + article.hit);
					/* 다중 예외 처리를 하려면 상위 예외 클래스가 하위 예외 클래스보다 아래에 위치해야 한다. */
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("명령어를 다시 입력해주세요.");
				} catch (IndexOutOfBoundsException e) {
					System.out.println(a + "번 게시물은 존재하지 않습니다.");
				} catch (NumberFormatException e) {
					System.out.println("detail의 뒤에는 숫자만 올수 있습니다.");
				}

			} else if (command.startsWith("article delete")) {

				try {
					String[] n = command.split(" ");
					a = Integer.parseInt(n[2]);
					int foundIndex = -1;
					for(int i = 0; i < articles.size(); i++) {
						Article article = articles.get(i);
						if(article.id == a) {
							foundIndex = i;
							break;
						}
					}
					articles.remove(foundIndex);
					System.out.println(a + "번 게시물이 삭제 되었습니다.");

				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("명령어를 다시 입력해주세요.");
				} catch (IndexOutOfBoundsException e) {
					System.out.println(a + "번 게시물은 존재하지 않습니다.");
				} catch (NumberFormatException e) {
					System.out.println("detail의 뒤에는 숫자만 올수 있습니다.");
				}
			} else if (command.startsWith("article modify")) {

				try {
					String[] n = command.split(" ");
					a = Integer.parseInt(n[2]);
					int foundIndex = -1;
					for(int i = 0; i < articles.size(); i++) {
						Article article = articles.get(i);
						if(article.id == a) {
							foundIndex = i;
							break;
						}
					}
					Article article = articles.get(foundIndex);
					System.out.print("제목 : ");
					String title = sc.nextLine();
					System.out.print("내용 : ");
					String body = sc.nextLine();
					Date date = new Date();
					article.body = body;
					article.title = title;
					article.regDate = Util.getNowDateTimeStr();
					System.out.printf("%d번글이 수정되었습니다\n", a);
					
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("명령어를 다시 입력해주세요.");
					continue;
				} catch (IndexOutOfBoundsException e) {
					System.out.println(a + "번 게시물은 존재하지 않습니다.");
					continue;
				} catch (NumberFormatException e) {
					System.out.println("detail의 뒤에는 숫자만 올수 있습니다.");
					continue;
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
	String regDate;
	int hit;

	public Article(int id, String title, String body, String regDate) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
	}

	public Article(int id, String title, String body, String regDate, int hit) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.hit = hit;
	}
}
#include <stdio.h>
#include <string.h>
#include <assert.h>

void reverse(char* str);
void swap_chars(char* str, int start, int end);
void run_tests();

int main(int argc, char** argv)
{
  	printf("%s\n", argv[1]);
	reverse(argv[1]);
	printf("%s\n", argv[1]);

	if (!strcmp(argv[1], "tset")) 
	{
		run_tests();
	}
  	return 0;
}

void reverse(char* str) 
{
	// I could have sworn I knew how to do this in one line...
	int i;
	for (i = 0; i < strlen(str)/2; i++) 
	{
		swap_chars(str, i, strlen(str) - i - 1);
	}
}

void swap_chars(char* str, int start, int end) 
{
	char temp = str[end];
	str[end] = str[start];
	str[start] = temp;
}

void run_tests() 
{
	printf("Running Tests\n");
	char testLongString[] = "This is a test string";
	char testShortString[] = "a";
	char testEvenString[] = "1234";
	char testOddString[] = "123";

	reverse(testLongString);
	reverse(testShortString);
	reverse(testEvenString);
	reverse(testOddString);

	assert(!strcmp(testLongString, "gnirts tset a si sihT"));
	assert(!strcmp(testShortString, "a"));
	assert(!strcmp(testEvenString, "4321"));
	assert(!strcmp(testOddString, "321"));	
}

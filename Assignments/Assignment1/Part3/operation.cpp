#include <iostream>
#include <cstdio>
#include <string>
using namespace std;
int main()
{
	freopen("part3.1.html", "r", stdin);
	freopen("part3.1.java", "w", stdout);
	string str;
	string s = "\"";
	while (getline(cin, str)) {
		if (str == "\t" || str == "")
			continue;
		for (int i = 0; i < str.length(); i++) {
			if (s.find(str[i]) != string::npos) {
				str.insert(i, "\\");
				i++;
			} 
		}
		cout << "\t\tout.println(\"" + str + "\");" << endl;
	}
}

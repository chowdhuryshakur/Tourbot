#include <bits/stdc++.h>
using namespace std;

class Test
{
    private:
        int data1;
        float data2;

    public:
        int function1()
        {   data1 = 9;
            return data1;}

        float function2()
        {
            data2 = 3.5;
            return data2;
        }
        void display(){
        cout<<data1;}
   }s[10];


int main(){
    Test s;
    s.display();
}

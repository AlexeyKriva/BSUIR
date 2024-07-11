//---------------------------------------------------------------------------

#include <vcl.h>
#pragma hdrstop

#include "Unit4.h"
#include <math.h>
//---------------------------------------------------------------------------
#pragma package(smart_init)
#pragma resource "*.dfm"
typedef double (*TFun)(double);
TForm1 *Form1;
double a, b, n, h;
//---------------------------------------------------------------------------
__fastcall TForm1::TForm1(TComponent* Owner)
        : TForm(Owner)
{
}
//---------------------------------------------------------------------------
double fact, s, y, sum, razn;
int i, k ;

double s(double x)
{
for(x=a;x<=b;x+=h)
{
 s=sum=0;
 for(k=0;k<=n;k++)
 {
  fact=1;
  for(i=1;i<=(2*k);i++)
  {
   fact*=i;
  }
  if(k%2==0)
  {
   s=((2*pow(k,2)+1)/fact)*pow(x,(2*k));
  }
  else
  {
   s=(-1)*((2*pow(k,2)+1)/fact)*pow(x,(2*k));
  }
   sum+=s;
 }
return sum;
}

double y(double x)
{
y=(1-pow(x,2)/2)*cos(x)-(x/2)*sin(x);
return y;
}

double fun1(double x)
{
return s(x);
}

double fun2(double x)
{
return y(x);
}

double fun3(double x)
{
return fabs(s(x)-y(x));
}

void Out_Rez(TFun fun,double a,double b,double h,TMemo* mem)
{
for (double x=a; x<=b; x+=h)
mem->Lines->Add("x="+FloatToStrF(x,ffFixed,8,8)+ " " + "y="+FloatToStrF(fun(x),ffFixed,8,8));
mem->Lines->Add(" ");
mem->Lines->Add(" ");
mem->Lines->Add(" ");
}

//---------------------------------------------------------------------------

void __fastcall TForm1::Button2Click(TObject *Sender)
{
Memo1->Clear();
Memo2->Clear();
Memo3->Clear();
}
//---------------------------------------------------------------------------

void __fastcall TForm1::Button1Click(TObject *Sender)
{
a=StrToFloat(Edit1->Text);
b=StrToFloat(Edit2->Text);
n=StrToFloat(Edit3->Text);
h=StrToFloat(Edit4->Text);
Memo1->Lines->Add("��� x= "+FloatToStrF(x,ffFixed,8,2)
                        +" sum= "+FloatToStrF(sum,ffFixed,8,5));
Memo2->Lines->Add("��� x= "+FloatToStrF(x,ffFixed,8,2)
                        +" y= "+FloatToStrF(y,ffFixed,8,5));
Memo3->Lines->Add("��� x= "+FloatToStrF(x,ffFixed,8,2)
                        +" razn= "+FloatToStrF(razn,ffFixed,8,5));
switch (RadioGroup1->ItemIndex)
{
case 0 : Out_Rez(fun1,a,b,h,Memo1); break;
case 1 : Out_Rez(fun2,a,b,h,Memo2); break;
case 2 : Out_Rez(fun3,a,b,h,Memo3); break;
}
}
//---------------------------------------------------------------------------

void __fastcall TForm1::FormCreate(TObject *Sender)
{
Edit1->Text="0,1";
Edit2->Text="1" ;
Edit3->Text="10";
Edit4->Text="0,1";
Memo1->Clear();
Memo2->Clear();
Memo3->Clear();
}
//---------------------------------------------------------------------------

//---------------------------------------------------------------------------

















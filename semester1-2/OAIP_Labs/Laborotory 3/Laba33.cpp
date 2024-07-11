//---------------------------------------------------------------------------
#include <iostream>
#include <math.h>
#include <vcl.h>
#pragma hdrstop
#include "Laba33.h"
//---------------------------------------------------------------------------
#pragma package(smart_init)
#pragma resource "*.dfm"
TForm1 *Form1;
//---------------------------------------------------------------------------
__fastcall TForm1::TForm1(TComponent* Owner)
        : TForm(Owner)
{
}
//---------------------------------------------------------------------------
void __fastcall TForm1::FormCreate(TObject *Sender)
{
Edit1->Text="0,1";
Edit2->Text="1,";
Edit3->Text="10";
Edit4->Text="0,1";
Memo1->Lines->Add("Laborotory work 3");
}
//---------------------------------------------------------------------------
void __fastcall TForm1::Button1Click(TObject *Sender)
{
double a, b, fact, h, s, y, x, sum, razn;
int i, n, k;
a=StrToFloat(Edit1->Text);
b=StrToFloat(Edit2->Text);
n=StrToFloat(Edit3->Text);
h=StrToFloat(Edit4->Text);
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
 y=(1-pow(x,2)/2)*cos(x)-(x/2)*sin(x);
 razn=fabs(sum-y);
 Memo1->Lines->Add("при x= "+FloatToStrF(x,ffFixed,8,2)
                        +" sum= "+FloatToStrF(sum,ffFixed,8,5)
                        +" y= "+FloatToStrF(y,ffFixed,8,5)
                        +" razn= "+FloatToStrF(razn,ffFixed,8,5));
}
}

//---------------------------------------------------------------------------
void __fastcall TForm1::Button2Click(TObject *Sender)
{
Memo1->Clear();
}
//---------------------------------------------------------------------------









#include "inputedit.h"
#include "ui_inputedit.h"
#include "runwindow.h"

#include <fstream>
#include <iostream>

inputEdit::inputEdit(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::inputEdit)
{
    ui->setupUi(this);
}

inputEdit::~inputEdit()
{
    delete ui;
}

void inputEdit::showInput(){
    std::ifstream infile("MyInput_GUI.txt");
   // QString openfile = "MyInput_GUI.txt";
    std::string line;
   // QByteArray ba = openfile.toLocal8Bit();
   // const char *openfileName = ba.data();
   // infile.open(openfileName);
    while (getline(infile, line)){
        ui->textEdit_inputFile->append(QString::fromStdString(line));
    }
    infile.close();
}

void inputEdit::writeToFile(QString savefile){
    QString data = ui->textEdit_inputFile->toPlainText();
    std::string utf8_data = data.toUtf8().constData();
    std::ofstream outfile;
    QByteArray ba = savefile.toLocal8Bit();
    const char *savefileName = ba.data();
    outfile.open(savefileName);
    outfile << utf8_data;
    outfile.close();
}

void inputEdit::on_pushButton_run_clicked()
{
    //this will need to save the file first or it won't work!!!
    writeToFile("MyInput_GUI.txt");
    runwindow = new runWindow(this);
   // runwindow->setWindowModality(Qt::ApplicationModal);
    runwindow->show();
    runwindow->runRADDOSE();
}

void inputEdit::on_pushButton_Back_clicked()
{
    close();
}


void inputEdit::on_pushButton_Save_clicked()
{
    QString savefile = QFileDialog::getSaveFileName(this, "Save a file",
    QDir::homePath());
    writeToFile(savefile);
}

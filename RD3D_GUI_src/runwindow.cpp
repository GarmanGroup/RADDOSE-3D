#include "runwindow.h"
#include "ui_runwindow.h"

runWindow::runWindow(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::runWindow)
{
    ui->setupUi(this);
}

runWindow::~runWindow()
{
    delete ui;
}

void runWindow::runRADDOSE(){
    QString inputFilee = "MyInput_GUI.txt";
    proc = new QProcess(this);
    QString command = "java";
    QStringList arguments;
    arguments << "-jar" << "raddose3d.jar" << "-i" << inputFilee;
    proc -> start(command,arguments);
    connect(proc, SIGNAL(readyReadStandardOutput()), this,SLOT(rightMessage()));
    connect(proc, SIGNAL(readyReadStandardError()), this,SLOT(wrongMessage()));
}

void runWindow::rightMessage(){
    QByteArray strdata = proc->readAllStandardOutput();
    ui->textEdit_output->append(strdata);
}

void runWindow::wrongMessage(){
    QByteArray strdata = proc->readAllStandardError();
    ui->textEdit_output->append(strdata);
}

void runWindow::on_pushButton_plotHist_clicked()
{
    QString inputFilee = "output-DoseState.R";
    proc = new QProcess(this);
    QString command = "Rscript";
    QStringList arguments;
    arguments << inputFilee;
    proc -> start(command,arguments);
    connect(proc, SIGNAL(readyReadStandardOutput()), this,SLOT(rightMessage()));
    connect(proc, SIGNAL(readyReadStandardError()), this,SLOT(wrongMessage()));
}

void runWindow::on_pushButton_runBack_clicked()
{

    close();
}

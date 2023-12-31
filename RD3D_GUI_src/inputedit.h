#ifndef INPUTEDIT_H
#define INPUTEDIT_H

#include <QDialog>
#include <QFileDialog>
#include <QDir>
#include "runwindow.h"

namespace Ui {
class inputEdit;
}

class inputEdit : public QDialog
{
    Q_OBJECT

public:
    explicit inputEdit(QWidget *parent = 0);
    void showInput();
    ~inputEdit();

private slots:
    void on_pushButton_run_clicked();

    void on_pushButton_Back_clicked();

    void on_pushButton_Save_clicked();

    void writeToFile(QString savefile);

private:
    Ui::inputEdit *ui;
    runWindow *runwindow;
};

#endif // INPUTEDIT_H

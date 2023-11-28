#ifndef RUNWINDOW_H
#define RUNWINDOW_H

#include <QDialog>
#include <QProcess>

namespace Ui {
class runWindow;
}

class runWindow : public QDialog
{
    Q_OBJECT

public:
    explicit runWindow(QWidget *parent = 0);
    void runRADDOSE();
    ~runWindow();

private slots:

    void rightMessage();
    void wrongMessage();

    void on_pushButton_plotHist_clicked();

    void on_pushButton_runBack_clicked();

private:
    Ui::runWindow *ui;
    QProcess *proc;
};

#endif // RUNWINDOW_H

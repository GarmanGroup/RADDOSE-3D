#include "crystaladvanced.h"
#include "ui_crystaladvanced.h"

#include <algorithm>
#include <regex>

extern bool simulation;

CrystalAdvanced::CrystalAdvanced(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::CrystalAdvanced)
{
    ui->setupUi(this);
    //this->setFixedSize(this->size().width(), this->size().height());
    QPalette *palette = new QPalette();
    ui->label_containerDensity->setVisible(false);
    ui->label_containerDensity->setText("Container Density (g/cm<sup>3</sup>");
    ui->label_containerThickness->setVisible(false);
    ui->label_containerThickness->setText( QString::fromUtf8( "Container thickness (\xce\xbc\m)" ) );
    ui->lineEdit_containerDensity->setVisible(false);
    ui->lineEdit_containerThickness->setVisible(false);
    ui->label_containerEl->setVisible(false);
    ui->label_containerNum->setVisible(false);
    ui->label_containerMixture->setVisible(false);
    ui->label_containerMatEl->setVisible(false);
    ui->label_containerMatEl2->setVisible(false);
    ui->label_atomList->setVisible(false);
    ui->textEdit_container->setVisible(false);
    ui->lineEdit_materialMixture->setVisible(false);
    ui->lineEdit_containerEl->setVisible(false);
    ui->lineEdit_containerNum->setVisible(false);
    ui->pushButton_container->setVisible(false);
    if (simulation == true){
        ui->label_containerParams->setVisible(false);
        ui->label_containerType->setVisible(false);
        ui->comboBox_containerType->setVisible(false);
        ui->comboBox_PE->setCurrentIndex(1);
        ui->comboBox_FLE->setCurrentIndex(1);
        ui->comboBox_PE->setEnabled(false);
        ui->comboBox_FLE->setEnabled(false);
        palette->setColor(QPalette::Base,Qt::gray);
        ui->comboBox_PE->setPalette(*palette);
        ui->comboBox_FLE->setPalette(*palette);
    }
    else {
        ui->label_goniometer->setVisible(false);
        ui->comboBox_goniometer->setVisible(false);
        ui->label_polarisation4->setVisible(false);
        ui->comboBox_polarisation->setVisible(false);
    }

   // ui->label_SurrConc->setVisible(false);

    ui->label_Density->setVisible(false);
    ui->label_Density->setText("Density (g/cm<sup>3</sup>)");
    ui->lineEdit_Density->setVisible(false);
    ui->label_densityDefine->setVisible(false);
    ui->comboBox_Density->setVisible(false);
    ui->label_SurrEl->setVisible(false);
    ui->label_SurrEl2->setVisible(false);
    ui->label_SurrElNum->setVisible(false);
    ui->label_SurrElSymbol->setVisible(false);
    ui->lineEdit_SurrElSymbol->setVisible(false);
    ui->lineEdit_SurrElNum->setVisible(false);
    ui->pushButton_SurrEl->setVisible(false);
    ui->label_SurrAtomList->setVisible(false);
    ui->textEdit_SurrEl->setVisible(false);
    ui->label_SurrEl->setText("Surrounding Elements");
    ui->label_SurrEl2->setText("Surrounding Elements");

    ui->label_angleP->setText( QString::fromUtf8( "AngleP (\xc2\xb0)" ) );
    ui->label_angleL->setText( QString::fromUtf8( "AngleL (\xc2\xb0)" ) );
}

CrystalAdvanced::~CrystalAdvanced()
{
    delete ui;
}

void CrystalAdvanced::on_pushButton_clicked()
{
    QString angleP, angleL, peEscape, flEscape, calcSurr, defineDensity, density, surrElements, goniometer, polaristion,
            containerType, containerThickness, containerDensity, matieralMixture, materialElements;
    std::regex newlines_re("\n+");

    angleP = ui->lineEdit_angleP->text();
    angleL =ui->lineEdit_angleL->text();
    peEscape = ui->comboBox_PE->currentText();
    flEscape = ui->comboBox_FLE->currentText();
    calcSurr = ui->comboBox_Surrounding->currentText();
    defineDensity = ui->comboBox_Density->currentText();
    density = ui->lineEdit_Density->text();
    surrElements = ui->textEdit_SurrEl->toPlainText();
    surrElements = QString::fromStdString(std::regex_replace(surrElements.toUtf8().constData(), newlines_re, " "));
    goniometer = ui->comboBox_goniometer->currentText();
    polaristion = ui->comboBox_polarisation->currentText();
    containerType = ui->comboBox_containerType->currentText();
    containerThickness = ui->lineEdit_containerThickness->text();
    containerDensity = ui->lineEdit_containerDensity->text();
    matieralMixture = ui->lineEdit_materialMixture->text();
    materialElements = ui->textEdit_container->toPlainText();
    materialElements = QString::fromStdString(std::regex_replace(materialElements.toUtf8().constData(), newlines_re, " "));
    emit sendData(angleP, angleL, peEscape, flEscape, calcSurr, defineDensity, density, surrElements, goniometer, polaristion,
                  containerType, containerThickness, containerDensity, matieralMixture, materialElements);
    close();
}
void CrystalAdvanced::clearMaterialEl(){
    ui->textEdit_container->clear();
}

void CrystalAdvanced::clearSurroundingEl(){
    ui->textEdit_SurrEl->clear();
}

void CrystalAdvanced::appendMaterialEl(QString materialElementsIn){
    ui->textEdit_container->append(materialElementsIn);
}

void CrystalAdvanced::appendSurroundingEl(QString surrElementsIn){
    ui->textEdit_SurrEl->append(surrElementsIn);
}
void CrystalAdvanced::updateData(QString anglePIn, QString angleLIn, QString peEscapeIn, QString flEscapeIn, QString calcSurrIn, QString defineDensityIn, QString densityIn,
                QString surrElementsIn, QString goniometerIn, QString polaristionIn, QString containerTypeIn, QString containerThicknessIn,
                                 QString containerDensityIn, QString matieralMixtureIn, QString materialElementsIn){
    ui->lineEdit_angleP->setText(anglePIn);
    ui->lineEdit_angleL->setText(angleLIn);
    ui->comboBox_PE->setCurrentText(peEscapeIn);
    on_comboBox_PE_activated(peEscapeIn);
    ui->comboBox_FLE->setCurrentText(flEscapeIn);
    ui->comboBox_Surrounding->setCurrentText(calcSurrIn);
    on_comboBox_Surrounding_activated(calcSurrIn);

    std::string utf8_arg1 = calcSurrIn.toUtf8().constData();
    std::transform(utf8_arg1.begin(), utf8_arg1.end(),utf8_arg1.begin(), ::toupper);
    QString arg1_upper = QString::fromStdString(utf8_arg1);
    if (arg1_upper == "True" || arg1_upper=="TRUE"){
        ui->comboBox_Density->setCurrentText(defineDensityIn);
        on_comboBox_Density_activated(defineDensityIn);
        ui->lineEdit_Density->setText(densityIn);
    }

    //ui->textEdit_SurrEl->clear();
    //ui->textEdit_SurrEl->setPlainText(surrElementsIn);
    ui->comboBox_goniometer->setCurrentText(goniometerIn);
    ui->comboBox_polarisation->setCurrentText(polaristionIn);
    ui->comboBox_containerType->setCurrentText(containerTypeIn);
    on_comboBox_containerType_activated(containerTypeIn);
    ui->lineEdit_containerThickness->setText(containerThicknessIn);
    ui->lineEdit_containerDensity->setText(containerDensityIn);
    ui->lineEdit_materialMixture->setText(matieralMixtureIn);
    //ui->textEdit_container->clear();
    //ui->textEdit_container->setPlainText(materialElementsIn);
}

void CrystalAdvanced::updateData2(bool loadContainer, bool loadPE, bool loadFL, bool loadSurr){
    if (loadContainer == false){
        ui->comboBox_containerType->setCurrentText("None");
        on_comboBox_containerType_activated("None");
    }
    if (loadPE == false){
        ui->comboBox_PE->setCurrentText("False");
        on_comboBox_PE_activated("False");
    }
    if (loadFL == false){
        ui->comboBox_FLE->setCurrentText("False");
    }
    if (loadSurr == false){
        ui->comboBox_Surrounding->setCurrentText("False");
        on_comboBox_Surrounding_activated("False");
    }

}

void CrystalAdvanced::on_comboBox_containerType_activated(const QString &arg1)
{
    std::string utf8_arg1 = arg1.toUtf8().constData();
    std::transform(utf8_arg1.begin(), utf8_arg1.end(),utf8_arg1.begin(), ::toupper);
    QString arg1_upper = QString::fromStdString(utf8_arg1);
    if (arg1_upper == "None" || arg1_upper=="NONE"){
        ui->comboBox_containerType->setCurrentText("None");
        ui->label_containerDensity->setVisible(false);
        ui->label_containerThickness->setVisible(false);
        ui->lineEdit_containerDensity->setVisible(false);
        ui->lineEdit_containerThickness->setVisible(false);
        ui->label_containerEl->setVisible(false);
        ui->label_containerNum->setVisible(false);
        ui->label_containerMixture->setVisible(false);
        ui->label_containerMatEl->setVisible(false);
        ui->label_containerMatEl2->setVisible(false);
        ui->label_atomList->setVisible(false);
        ui->textEdit_container->setVisible(false);
        ui->lineEdit_materialMixture->setVisible(false);
        ui->lineEdit_containerEl->setVisible(false);
        ui->lineEdit_containerNum->setVisible(false);
        ui->pushButton_container->setVisible(false);
    }
    else if (arg1_upper == "Mixture" || arg1_upper=="MIXTURE"){
        ui->comboBox_containerType->setCurrentText("Mixture");
        ui->label_containerDensity->setVisible(true);
        ui->label_containerThickness->setVisible(true);
        ui->lineEdit_containerDensity->setVisible(true);
        ui->lineEdit_containerThickness->setVisible(true);
        ui->label_containerEl->setVisible(false);
        ui->label_containerNum->setVisible(false);
        ui->label_containerMixture->setVisible(true);
        ui->label_containerMatEl->setVisible(false);
        ui->label_containerMatEl2->setVisible(false);
        ui->label_atomList->setVisible(false);
        ui->textEdit_container->setVisible(false);
        ui->lineEdit_materialMixture->setVisible(true);
        ui->lineEdit_containerEl->setVisible(false);
        ui->lineEdit_containerNum->setVisible(false);
        ui->pushButton_container->setVisible(false);
    }
    else if (arg1_upper == "Elements" || arg1_upper=="ELEMENTS"){
        ui->comboBox_containerType->setCurrentText("Elements");
        ui->label_containerDensity->setVisible(true);
        ui->label_containerThickness->setVisible(true);
        ui->lineEdit_containerDensity->setVisible(true);
        ui->lineEdit_containerThickness->setVisible(true);
        ui->label_containerEl->setVisible(true);
        ui->label_containerNum->setVisible(true);
        ui->label_containerMixture->setVisible(false);
        ui->label_containerMatEl->setVisible(true);
        ui->label_containerMatEl2->setVisible(true);
        ui->label_atomList->setVisible(true);
        ui->textEdit_container->setVisible(true);
        ui->lineEdit_materialMixture->setVisible(false);
        ui->lineEdit_containerEl->setVisible(true);
        ui->lineEdit_containerNum->setVisible(true);
        ui->pushButton_container->setVisible(true);
    }
}

void CrystalAdvanced::on_comboBox_Density_activated(const QString &arg1)
{
    std::string utf8_arg1 = arg1.toUtf8().constData();
    std::transform(utf8_arg1.begin(), utf8_arg1.end(),utf8_arg1.begin(), ::toupper);
    QString arg1_upper = QString::fromStdString(utf8_arg1);
    if (arg1_upper=="Yes" || arg1_upper=="YES"){
        ui->comboBox_Density->setCurrentText("Yes");
        ui->label_SurrEl->setVisible(true);
        ui->label_SurrEl->setText("Surrounding Elements");
        ui->label_SurrEl2->setVisible(true);
        ui->label_SurrEl2->setText("Surrounding Elements");
        ui->label_Density->setVisible(true);
        ui->lineEdit_Density->setVisible(true);
      //  ui->label_SurrConc->setVisible(false);
    }
    else if (arg1_upper=="No" || arg1_upper=="NO"){
        ui->comboBox_Density->setCurrentText("No");
        ui->label_SurrEl->setVisible(true);
        ui->label_SurrEl->setText("Surrounding Concentration (mM)");
        ui->label_SurrEl2->setVisible(true);
        ui->label_SurrEl2->setText("Surrounding Concentration (mM)");
        ui->label_Density->setVisible(false);
        ui->lineEdit_Density->setVisible(false);
     //   ui->label_SurrConc->setVisible(true);
    }
}

void CrystalAdvanced::on_comboBox_PE_activated(const QString &arg1)
{
    std::string utf8_arg1 = arg1.toUtf8().constData();
    std::transform(utf8_arg1.begin(), utf8_arg1.end(),utf8_arg1.begin(), ::toupper);
    QString arg1_upper = QString::fromStdString(utf8_arg1);
    if (arg1_upper=="True" || arg1_upper=="TRUE"){
        ui->comboBox_PE->setCurrentText("True");
        ui->label_goniometer->setVisible(true);
        ui->comboBox_goniometer->setVisible(true);
        ui->label_polarisation4->setVisible(true);
        ui->comboBox_polarisation->setVisible(true);
    }
    else if (arg1_upper=="False" || arg1_upper == "FALSE"){
        ui->comboBox_PE->setCurrentText("False");
        ui->label_goniometer->setVisible(false);
        ui->comboBox_goniometer->setVisible(false);
        ui->label_polarisation4->setVisible(false);
        ui->comboBox_polarisation->setVisible(false);
    }
}

void CrystalAdvanced::on_pushButton_SurrEl_clicked()
{
    QString El = ui->lineEdit_SurrElSymbol->text();
    QString Num = ui->lineEdit_SurrElNum->text();
    QString toAdd = El + " " + Num;
    ui->textEdit_SurrEl->append(toAdd);
}

void CrystalAdvanced::on_pushButton_container_clicked()
{
    QString El = ui->lineEdit_containerEl->text();
    QString Num = ui->lineEdit_containerNum->text();
    QString toAdd = El + " " + Num;
    ui->textEdit_container->append(toAdd);
}

void CrystalAdvanced::on_comboBox_Surrounding_activated(const QString &arg1)
{
    std::string utf8_arg1 = arg1.toUtf8().constData();
    std::transform(utf8_arg1.begin(), utf8_arg1.end(),utf8_arg1.begin(), ::toupper);
    QString arg1_upper = QString::fromStdString(utf8_arg1);
    if (arg1_upper=="True" || arg1_upper=="TRUE"){
        ui->comboBox_Surrounding->setCurrentText("True");
        ui->label_Density->setVisible(true);
        ui->lineEdit_Density->setVisible(true);
        ui->label_densityDefine->setVisible(true);
        ui->comboBox_Density->setVisible(true);
        ui->label_SurrEl->setVisible(true);
        ui->label_SurrEl2->setVisible(true);
        ui->label_SurrElNum->setVisible(true);
        ui->label_SurrElSymbol->setVisible(true);
        ui->lineEdit_SurrElSymbol->setVisible(true);
        ui->lineEdit_SurrElNum->setVisible(true);
        ui->pushButton_SurrEl->setVisible(true);
        ui->label_SurrAtomList->setVisible(true);
        ui->textEdit_SurrEl->setVisible(true);
    }
    else if (arg1_upper=="False" || arg1_upper=="FALSE"){
        ui->comboBox_Surrounding->setCurrentText("False");
        ui->label_Density->setVisible(false);
        ui->lineEdit_Density->setVisible(false);
        ui->label_densityDefine->setVisible(false);
        ui->comboBox_Density->setVisible(false);
        ui->label_SurrEl->setVisible(false);
        ui->label_SurrEl2->setVisible(false);
        ui->label_SurrElNum->setVisible(false);
        ui->label_SurrElSymbol->setVisible(false);
        ui->lineEdit_SurrElSymbol->setVisible(false);
        ui->lineEdit_SurrElNum->setVisible(false);
        ui->pushButton_SurrEl->setVisible(false);
        ui->label_SurrAtomList->setVisible(false);
        ui->textEdit_SurrEl->setVisible(false);
    }
}

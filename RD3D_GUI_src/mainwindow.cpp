#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "crystaladvanced.h"
#include "runwindow.h"
#include "inputedit.h"

#include <fstream>
#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
#include <QDesktopServices>
#include <QUrl>

#include <algorithm>
#include <regex>

bool simulation;
bool loadSim = false;
bool loadContainerType = false;
bool loadPE = false;
bool loadFL = false;
bool loadSurr = false;
bool loadColl = false;
bool loadDensity = false;

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    QPalette *palette = new QPalette();
    palette->setColor(QPalette::Base,Qt::gray);
    ui->setupUi(this);
    //this->setFixedSize(this->size().width(), this->size().height());
    ui->label_Modelfile->setVisible(false);
    ui->lineEdit_modelfile->setVisible(false);
    ui->pushButton_browsemodel->setVisible(false);
    ui->label_PDB->setVisible(false);
    ui->lineEdit_PDB->setVisible(false);
    ui->label_fasta->setVisible(false);
    ui->lineEdit_fasta->setVisible(false);
    ui->pushButton_browseFasta->setVisible(false);
    ui->label_pConc->setVisible(false);
    ui->lineEdit_pConc->setVisible(false);
    ui->label_CIF->setVisible(false);
    ui->lineEdit_CIF->setVisible(false);
    ui->pushButton_CIF->setVisible(false);
    ui->label_photons->setVisible(false);
    ui->lineEdit_photons->setVisible(false);
    ui->label_runs->setVisible(false);
    ui->lineEdit_runs->setVisible(false);
    ui->label_Dims->setText( QString::fromUtf8( "Dimensions (\xce\xbc\m)" ) );
    ui->label_UnitCell->setText( QString::fromUtf8( "Unit cell (\xe2\x84\xab)" ) );
    ui->label_pConc->setText("Protein Conc (mg/ml)");

    //beam stuff
    ui->label_beamFile->setVisible(false);
    ui->lineEdit_beamFile->setVisible(false);
    ui->pushButton_beamFile->setVisible(false);
    ui->label_pxSize->setVisible(false);
    ui->lineEdit_pxSizeX->setVisible(false);
    ui->lineEdit_pxSizeY->setVisible(false);
    ui->label_pxX->setVisible(false);
    ui->label_pxY->setVisible(false);
    ui->label_pulseEn->setVisible(false);
    ui->lineEdit_pulseEn->setVisible(false);
    ui->label_exposure->setVisible(false);
    ui->lineEdit_exposure->setVisible(false);
    ui->lineEdit_beamEnFWHM->setReadOnly(true);
    ui->lineEdit_beamEnFWHM->setPalette(*palette);
    ui->label_pxSize->setText( QString::fromUtf8( "Pixel size (\xce\xbc\m)" ) );
    ui->label_FWHM->setText( QString::fromUtf8( "FWHM (\xce\xbc\m)" ) );
    ui->label_collDims->setText( QString::fromUtf8( "Dimensions (\xce\xbc\m)" ) );

    //wedge stuff
    //ui->label_pulseLength->setVisible(false);
    ui->label_startOffset->setText( QString::fromUtf8( "Starting offset (\xce\xbc\m)" ) );
    ui->label_tns->setText( QString::fromUtf8( "Translation per degree (\xce\xbc\m)" ) );
    ui->label_rotOffset->setText( QString::fromUtf8( "Rotation offset (\xce\xbc\m)" ) );
    ui->label_startAngle->setText( QString::fromUtf8( "Start Angle (\xc2\xb0)" ) );
    ui->label_endAngle->setText( QString::fromUtf8( "End Angle (\xc2\xb0)" ) );
    ui->label_angleRes->setText( QString::fromUtf8( "Angular Resolution (\xc2\xb0)" ) );
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_comboBox_activated(const QString &arg1)
{
    std::string utf8_arg1 = arg1.toUtf8().constData();
    std::transform(utf8_arg1.begin(), utf8_arg1.end(),utf8_arg1.begin(), ::toupper);
    QString arg1_upper = QString::fromStdString(utf8_arg1);
    if (arg1_upper == "Spherical" || arg1_upper=="SPHERICAL"){
        ui->comboBox->setCurrentText("Spherical");
        ui->label_Dims->setVisible(true);
        ui->label_X->setText("D");
        ui->label_X->setVisible(true);
        ui->label_Y->setVisible(false);
        ui->label_Z->setVisible(false);
        ui->lineEdit_X->setVisible(true);
        ui->lineEdit_Y->setVisible(false);
        ui->lineEdit_Z->setVisible(false);



        ui->label_Modelfile->setVisible(false);
        ui->lineEdit_modelfile->setVisible(false);
        ui->pushButton_browsemodel->setVisible(false);
    }
    else if (arg1_upper == "Cuboid" || arg1_upper=="CUBOID"){
        ui->comboBox->setCurrentText("Cuboid");
        ui->label_Dims->setVisible(true);
        ui->label_X->setVisible(true);
        ui->label_X->setText("X");
        ui->label_Y->setVisible(true);
        ui->label_Y->setText("Y");
        ui->label_Z->setVisible(true);
        ui->label_Z->setText("Z");
        ui->lineEdit_X->setVisible(true);
        ui->lineEdit_Y->setVisible(true);
        ui->lineEdit_Z->setVisible(true);

        ui->label_Modelfile->setVisible(false);
        ui->lineEdit_modelfile->setVisible(false);
        ui->pushButton_browsemodel->setVisible(false);
    }
    else if (arg1_upper == "Cylinder" || arg1_upper=="CYLINDER"){
        ui->comboBox->setCurrentText("Cylinder");
        ui->label_Dims->setVisible(true);
        ui->label_X->setVisible(true);
        ui->label_X->setText("D");
        ui->label_Y->setVisible(true);
        ui->label_Y->setText("H");
        ui->label_Z->setVisible(false);
        ui->lineEdit_X->setVisible(true);
        ui->lineEdit_Y->setVisible(true);
        ui->lineEdit_Z->setVisible(false);


        ui->label_Modelfile->setVisible(false);
        ui->lineEdit_modelfile->setVisible(false);
        ui->pushButton_browsemodel->setVisible(false);
    }
    else if (arg1_upper == "Polyhedron" || arg1_upper=="POLYHEDRON"){
        ui->comboBox->setCurrentText("Polyhedron");
        ui->label_Dims->setVisible(false);
        ui->label_X->setVisible(false);
        ui->label_Y->setVisible(false);
        ui->label_Z->setVisible(false);
        ui->lineEdit_X->setVisible(false);
        ui->lineEdit_Y->setVisible(false);
        ui->lineEdit_Z->setVisible(false);

        ui->label_Modelfile->setVisible(true);
        ui->lineEdit_modelfile->setVisible(true);
        ui->pushButton_browsemodel->setVisible(true);
    }
}

void MainWindow::on_pushButton_browsemodel_clicked()
{
    QString file_name = QFileDialog::getOpenFileName(this, "Open a file", QDir::homePath());
    ui->lineEdit_modelfile->setText(file_name);
}

void MainWindow::on_pushButton_heavyProt_clicked()
{
    QString El = ui->lineEdit_ElheavyProt->text();
    QString Num = ui->lineEdit_ElheavyProt_2->text();
    QString toAdd = El + " " + Num;
    ui->textEdit_heavyProt->append(toAdd);
}

void MainWindow::on_comboBox_AbsCoef_activated(const QString &arg1)
{
    std::string utf8_arg1 = arg1.toUtf8().constData();
    std::transform(utf8_arg1.begin(), utf8_arg1.end(),utf8_arg1.begin(), ::toupper);
    QString arg1_upper = QString::fromStdString(utf8_arg1);
    if (arg1_upper == "RD3D" || arg1_upper=="rd3d"){
        ui->label_UnitCell->setVisible(true);
        ui->label_a->setVisible(true);
        ui->label_b->setVisible(true);
        ui->label_c->setVisible(true);
        ui->label_alpha->setVisible(true);
        ui->label_beta->setVisible(true);
        ui->label_gamma->setVisible(true);
        ui->lineEdit_a->setVisible(true);
        ui->lineEdit_b->setVisible(true);
        ui->lineEdit_c->setVisible(true);
        ui->lineEdit_alpha->setVisible(true);
        ui->lineEdit_beta->setVisible(true);
        ui->lineEdit_gamma->setVisible(true);
        ui->label_Monomers->setVisible(true);
        ui->lineEdit_Monomers->setVisible(true);
        ui->label_Residues->setVisible(true);
        ui->lineEdit_Residues->setVisible(true);
        ui->label_heavyProt->setVisible(true);
        ui->label_heavyProt->setText("Heavy atoms per monomer");
        ui->label_heavyProt2->setVisible(true);
        ui->label_heavyProt2->setText("Heavy atoms per monomer");
        ui->label_heavyEl->setVisible(true);
        ui->label_heavyNum->setVisible(true);
        ui->label_heavyTitle->setVisible(true);
        ui->label_heavyTitle->setText("Heavy Atom List");
        ui->lineEdit_ElheavyProt->setVisible(true);
        ui->lineEdit_ElheavyProt_2->setVisible(true);
        ui->pushButton_heavyProt->setVisible(true);
        ui->textEdit_heavyProt->setVisible(true);
        ui->label_solventEl->setVisible(true);
        ui->label_Solvent->setVisible(true);
        ui->label_Solvent2->setVisible(true);
        ui->label_solventConc->setVisible(true);
        ui->label_solventTitle->setVisible(true);
        ui->lineEdit_Elsolvent->setVisible(true);
        ui->lineEdit_Elconc->setVisible(true);
        ui->textEdit_solventConc->setVisible(true);
        ui->pushButton_solventConc->setVisible(true);
        ui->label_SolventFr->setVisible(true);
        ui->lineEdit_solventFr->setVisible(true);
        ui->label_Nucleotides->setVisible(true);
        ui->label_DNA->setVisible(true);
        ui->label_RNA->setVisible(true);
        ui->lineEdit_DNA->setVisible(true);
        ui->lineEdit_RNA->setVisible(true);
        ui->label_carb->setVisible(true);
        ui->lineEdit_carb->setVisible(true);
        ui->label_PDB->setVisible(false);
        ui->lineEdit_PDB->setVisible(false);
        ui->label_fasta->setVisible(false);
        ui->lineEdit_fasta->setVisible(false);
        ui->pushButton_browseFasta->setVisible(false);
        ui->label_pConc->setVisible(false);
        ui->lineEdit_pConc->setVisible(false);
        ui->label_CIF->setVisible(false);
        ui->lineEdit_CIF->setVisible(false);
        ui->pushButton_CIF->setVisible(false);
    }
    else if (arg1_upper == "EXP"){
        ui->label_UnitCell->setVisible(false);
        ui->label_a->setVisible(false);
        ui->label_b->setVisible(false);
        ui->label_c->setVisible(false);
        ui->label_alpha->setVisible(false);
        ui->label_beta->setVisible(false);
        ui->label_gamma->setVisible(false);
        ui->lineEdit_a->setVisible(false);
        ui->lineEdit_b->setVisible(false);
        ui->lineEdit_c->setVisible(false);
        ui->lineEdit_alpha->setVisible(false);
        ui->lineEdit_beta->setVisible(false);
        ui->lineEdit_gamma->setVisible(false);
        ui->label_Monomers->setVisible(false);
        ui->lineEdit_Monomers->setVisible(false);
        ui->label_Residues->setVisible(false);
        ui->lineEdit_Residues->setVisible(false);
        ui->label_heavyProt->setVisible(false);
        ui->label_heavyProt2->setVisible(false);
        ui->label_heavyEl->setVisible(false);
        ui->label_heavyNum->setVisible(false);
        ui->label_heavyTitle->setVisible(false);
        ui->lineEdit_ElheavyProt->setVisible(false);
        ui->lineEdit_ElheavyProt_2->setVisible(false);
        ui->pushButton_heavyProt->setVisible(false);
        ui->textEdit_heavyProt->setVisible(false);
        ui->label_solventEl->setVisible(true);
        ui->label_Solvent->setVisible(true);
        ui->label_Solvent2->setVisible(true);
        ui->label_solventConc->setVisible(true);
        ui->label_solventTitle->setVisible(true);
        ui->lineEdit_Elsolvent->setVisible(true);
        ui->lineEdit_Elconc->setVisible(true);
        ui->textEdit_solventConc->setVisible(true);
        ui->pushButton_solventConc->setVisible(true);
        ui->label_SolventFr->setVisible(false);
        ui->lineEdit_solventFr->setVisible(false);
        ui->label_Nucleotides->setVisible(false);
        ui->label_DNA->setVisible(false);
        ui->label_RNA->setVisible(false);
        ui->lineEdit_DNA->setVisible(false);
        ui->lineEdit_RNA->setVisible(false);
        ui->label_carb->setVisible(false);
        ui->lineEdit_carb->setVisible(false);
        ui->label_PDB->setVisible(true);
        ui->label_PDB->move(10,130);
        ui->lineEdit_PDB->move(100,130);
        ui->lineEdit_PDB->setVisible(true);
        ui->label_fasta->setVisible(false);
        ui->lineEdit_fasta->setVisible(false);
        ui->pushButton_browseFasta->setVisible(false);
        ui->label_pConc->setVisible(false);
        ui->lineEdit_pConc->setVisible(false);
        ui->label_CIF->setVisible(false);
        ui->lineEdit_CIF->setVisible(false);
        ui->pushButton_CIF->setVisible(false);
    }
    else if (arg1_upper == "SEQUENCE"){
        ui->label_UnitCell->setVisible(true);
        ui->label_a->setVisible(true);
        ui->label_b->setVisible(true);
        ui->label_c->setVisible(true);
        ui->label_alpha->setVisible(true);
        ui->label_beta->setVisible(true);
        ui->label_gamma->setVisible(true);
        ui->lineEdit_a->setVisible(true);
        ui->lineEdit_b->setVisible(true);
        ui->lineEdit_c->setVisible(true);
        ui->lineEdit_alpha->setVisible(true);
        ui->lineEdit_beta->setVisible(true);
        ui->lineEdit_gamma->setVisible(true);
        ui->label_Monomers->setVisible(true);
        ui->lineEdit_Monomers->setVisible(true);
        ui->label_Residues->setVisible(false);
        ui->lineEdit_Residues->setVisible(false);
        ui->label_heavyProt->setVisible(false);
        ui->label_heavyProt2->setVisible(false);
        ui->label_heavyEl->setVisible(false);
        ui->label_heavyNum->setVisible(false);
        ui->label_heavyTitle->setVisible(false);
        ui->lineEdit_ElheavyProt->setVisible(false);
        ui->lineEdit_ElheavyProt_2->setVisible(false);
        ui->pushButton_heavyProt->setVisible(false);
        ui->textEdit_heavyProt->setVisible(false);
        ui->label_solventEl->setVisible(true);
        ui->label_Solvent->setVisible(true);
        ui->label_Solvent2->setVisible(true);
        ui->label_solventConc->setVisible(true);
        ui->label_solventTitle->setVisible(true);
        ui->lineEdit_Elsolvent->setVisible(true);
        ui->lineEdit_Elconc->setVisible(true);
        ui->textEdit_solventConc->setVisible(true);
        ui->pushButton_solventConc->setVisible(true);
        ui->label_SolventFr->setVisible(true);
        ui->lineEdit_solventFr->setVisible(true);
        ui->label_Nucleotides->setVisible(false);
        ui->label_DNA->setVisible(false);
        ui->label_RNA->setVisible(false);
        ui->lineEdit_DNA->setVisible(false);
        ui->lineEdit_RNA->setVisible(false);
        ui->label_carb->setVisible(false);
        ui->lineEdit_carb->setVisible(false);
        ui->label_PDB->setVisible(false);
        ui->lineEdit_PDB->setVisible(false);
        ui->label_fasta->setVisible(true);
        ui->lineEdit_fasta->setVisible(true);
        ui->pushButton_browseFasta->setVisible(true);
        ui->label_fasta->move(10,190);
        ui->lineEdit_fasta->move(100,190);
        ui->pushButton_browseFasta->move(490,190);
        ui->label_pConc->setVisible(false);
        ui->lineEdit_pConc->setVisible(false);
        ui->label_CIF->setVisible(false);
        ui->lineEdit_CIF->setVisible(false);
        ui->pushButton_CIF->setVisible(false);
    }
    else if(arg1_upper=="SAXS"){
        ui->label_UnitCell->setVisible(true);
        ui->label_a->setVisible(true);
        ui->label_b->setVisible(true);
        ui->label_c->setVisible(true);
        ui->label_alpha->setVisible(true);
        ui->label_beta->setVisible(true);
        ui->label_gamma->setVisible(true);
        ui->lineEdit_a->setVisible(true);
        ui->lineEdit_b->setVisible(true);
        ui->lineEdit_c->setVisible(true);
        ui->lineEdit_alpha->setVisible(true);
        ui->lineEdit_beta->setVisible(true);
        ui->lineEdit_gamma->setVisible(true);
        ui->label_Monomers->setVisible(false);
        ui->lineEdit_Monomers->setVisible(false);
        ui->label_Residues->setVisible(true);
        ui->lineEdit_Residues->setVisible(true);
        ui->label_heavyProt->setVisible(true);
        ui->label_heavyProt->setText("Heavy atoms per monomer");
        ui->label_heavyProt2->setVisible(true);
        ui->label_heavyProt2->setText("Heavy atoms per monomer");
        ui->label_heavyEl->setVisible(true);
        ui->label_heavyNum->setVisible(true);
        ui->label_heavyTitle->setVisible(true);
        ui->label_heavyTitle->setText("Heavy Atom List");
        ui->lineEdit_ElheavyProt->setVisible(true);
        ui->lineEdit_ElheavyProt_2->setVisible(true);
        ui->pushButton_heavyProt->setVisible(true);
        ui->textEdit_heavyProt->setVisible(true);
        ui->label_solventEl->setVisible(true);
        ui->label_Solvent->setVisible(true);
        ui->label_Solvent2->setVisible(true);
        ui->label_solventConc->setVisible(true);
        ui->label_solventTitle->setVisible(true);
        ui->lineEdit_Elsolvent->setVisible(true);
        ui->lineEdit_Elconc->setVisible(true);
        ui->textEdit_solventConc->setVisible(true);
        ui->pushButton_solventConc->setVisible(true);
        ui->label_SolventFr->setVisible(true);
        ui->lineEdit_solventFr->setVisible(true);
        ui->label_Nucleotides->setVisible(true);
        ui->label_DNA->setVisible(true);
        ui->label_RNA->setVisible(true);
        ui->lineEdit_DNA->setVisible(true);
        ui->lineEdit_RNA->setVisible(true);
        ui->label_carb->setVisible(true);
        ui->lineEdit_carb->setVisible(true);
        ui->label_PDB->setVisible(false);
        ui->lineEdit_PDB->setVisible(false);
        ui->label_fasta->setVisible(false);
        ui->lineEdit_fasta->setVisible(false);
        ui->pushButton_browseFasta->setVisible(false);
        ui->label_pConc->setVisible(true);
        ui->lineEdit_pConc->setVisible(true);
        ui->label_CIF->setVisible(false);
        ui->lineEdit_CIF->setVisible(false);
        ui->pushButton_CIF->setVisible(false);
    }
    else if(arg1_upper=="SAXSSEQ"){
        ui->label_UnitCell->setVisible(true);
        ui->label_a->setVisible(true);
        ui->label_b->setVisible(true);
        ui->label_c->setVisible(true);
        ui->label_alpha->setVisible(true);
        ui->label_beta->setVisible(true);
        ui->label_gamma->setVisible(true);
        ui->lineEdit_a->setVisible(true);
        ui->lineEdit_b->setVisible(true);
        ui->lineEdit_c->setVisible(true);
        ui->lineEdit_alpha->setVisible(true);
        ui->lineEdit_beta->setVisible(true);
        ui->lineEdit_gamma->setVisible(true);
        ui->label_Monomers->setVisible(false);
        ui->lineEdit_Monomers->setVisible(false);
        ui->label_Residues->setVisible(false);
        ui->lineEdit_Residues->setVisible(false);
        ui->label_heavyProt->setVisible(false);
        ui->label_heavyProt2->setVisible(false);
        ui->label_heavyEl->setVisible(false);
        ui->label_heavyNum->setVisible(false);
        ui->label_heavyTitle->setVisible(false);
        ui->lineEdit_ElheavyProt->setVisible(false);
        ui->lineEdit_ElheavyProt_2->setVisible(false);
        ui->pushButton_heavyProt->setVisible(false);
        ui->textEdit_heavyProt->setVisible(false);
        ui->label_solventEl->setVisible(true);
        ui->label_Solvent->setVisible(true);
        ui->label_Solvent2->setVisible(true);
        ui->label_solventConc->setVisible(true);
        ui->label_solventTitle->setVisible(true);
        ui->lineEdit_Elsolvent->setVisible(true);
        ui->lineEdit_Elconc->setVisible(true);
        ui->textEdit_solventConc->setVisible(true);
        ui->pushButton_solventConc->setVisible(true);
        ui->label_SolventFr->setVisible(true);
        ui->lineEdit_solventFr->setVisible(true);
        ui->label_Nucleotides->setVisible(false);
        ui->label_DNA->setVisible(false);
        ui->label_RNA->setVisible(false);
        ui->lineEdit_DNA->setVisible(false);
        ui->lineEdit_RNA->setVisible(false);
        ui->label_carb->setVisible(false);
        ui->lineEdit_carb->setVisible(false);
        ui->label_PDB->setVisible(false);
        ui->lineEdit_PDB->setVisible(false);
        ui->label_fasta->setVisible(true);
        ui->lineEdit_fasta->setVisible(true);
        ui->pushButton_browseFasta->setVisible(true);
        ui->label_fasta->move(10,190);
        ui->lineEdit_fasta->move(100,190);
        ui->pushButton_browseFasta->move(490,190);
        ui->label_pConc->setVisible(true);
        ui->lineEdit_pConc->setVisible(true);
        ui->label_CIF->setVisible(false);
        ui->lineEdit_CIF->setVisible(false);
        ui->pushButton_CIF->setVisible(false);
    }
    else if (arg1_upper == "SMALLMOLE"){
        ui->label_UnitCell->setVisible(true);
        ui->label_a->setVisible(true);
        ui->label_b->setVisible(true);
        ui->label_c->setVisible(true);
        ui->label_alpha->setVisible(true);
        ui->label_beta->setVisible(true);
        ui->label_gamma->setVisible(true);
        ui->lineEdit_a->setVisible(true);
        ui->lineEdit_b->setVisible(true);
        ui->lineEdit_c->setVisible(true);
        ui->lineEdit_alpha->setVisible(true);
        ui->lineEdit_beta->setVisible(true);
        ui->lineEdit_gamma->setVisible(true);
        ui->label_Monomers->setVisible(true);
        ui->lineEdit_Monomers->setVisible(true);
        ui->label_Residues->setVisible(false);
        ui->lineEdit_Residues->setVisible(false);
        ui->label_heavyProt->setVisible(true);
        ui->label_heavyProt->setText("Heavy atoms per monomer");
        ui->label_heavyProt2->setVisible(true);
        ui->label_heavyProt2->setText("Heavy atoms per monomer");
        ui->label_heavyEl->setVisible(true);
        ui->label_heavyNum->setVisible(true);
        ui->label_heavyTitle->setVisible(true);
        ui->label_heavyTitle->setText("Atom List");
        ui->lineEdit_ElheavyProt->setVisible(true);
        ui->lineEdit_ElheavyProt_2->setVisible(true);
        ui->pushButton_heavyProt->setVisible(true);
        ui->textEdit_heavyProt->setVisible(true);
        ui->label_solventEl->setVisible(false);
        ui->label_Solvent->setVisible(false);
        ui->label_Solvent2->setVisible(false);
        ui->label_solventConc->setVisible(false);
        ui->label_solventTitle->setVisible(false);
        ui->lineEdit_Elsolvent->setVisible(false);
        ui->lineEdit_Elconc->setVisible(false);
        ui->textEdit_solventConc->setVisible(false);
        ui->pushButton_solventConc->setVisible(false);
        ui->label_SolventFr->setVisible(false);
        ui->lineEdit_solventFr->setVisible(false);
        ui->label_Nucleotides->setVisible(false);
        ui->label_DNA->setVisible(false);
        ui->label_RNA->setVisible(false);
        ui->lineEdit_DNA->setVisible(false);
        ui->lineEdit_RNA->setVisible(false);
        ui->label_carb->setVisible(false);
        ui->lineEdit_carb->setVisible(false);
        ui->label_PDB->setVisible(false);
        ui->lineEdit_PDB->setVisible(false);
        ui->label_fasta->setVisible(false);
        ui->lineEdit_fasta->setVisible(false);
        ui->pushButton_browseFasta->setVisible(false);
        ui->label_pConc->setVisible(false);
        ui->lineEdit_pConc->setVisible(false);
        ui->label_CIF->setVisible(false);
        ui->lineEdit_CIF->setVisible(false);
        ui->pushButton_CIF->setVisible(false);
    }
    else if (arg1_upper == "CIF"){
        ui->label_UnitCell->setVisible(false);
        ui->label_a->setVisible(false);
        ui->label_b->setVisible(false);
        ui->label_c->setVisible(false);
        ui->label_alpha->setVisible(false);
        ui->label_beta->setVisible(false);
        ui->label_gamma->setVisible(false);
        ui->lineEdit_a->setVisible(false);
        ui->lineEdit_b->setVisible(false);
        ui->lineEdit_c->setVisible(false);
        ui->lineEdit_alpha->setVisible(false);
        ui->lineEdit_beta->setVisible(false);
        ui->lineEdit_gamma->setVisible(false);
        ui->label_Monomers->setVisible(false);
        ui->lineEdit_Monomers->setVisible(false);
        ui->label_Residues->setVisible(false);
        ui->lineEdit_Residues->setVisible(false);
        ui->label_heavyProt->setVisible(false);
        ui->label_heavyProt2->setVisible(false);
        ui->label_heavyEl->setVisible(false);
        ui->label_heavyNum->setVisible(false);
        ui->label_heavyTitle->setVisible(false);
        ui->lineEdit_ElheavyProt->setVisible(false);
        ui->lineEdit_ElheavyProt_2->setVisible(false);
        ui->pushButton_heavyProt->setVisible(false);
        ui->textEdit_heavyProt->setVisible(false);
        ui->label_solventEl->setVisible(false);
        ui->label_Solvent->setVisible(false);
        ui->label_Solvent2->setVisible(false);
        ui->label_solventConc->setVisible(false);
        ui->label_solventTitle->setVisible(false);
        ui->lineEdit_Elsolvent->setVisible(false);
        ui->lineEdit_Elconc->setVisible(false);
        ui->textEdit_solventConc->setVisible(false);
        ui->pushButton_solventConc->setVisible(false);
        ui->label_SolventFr->setVisible(false);
        ui->lineEdit_solventFr->setVisible(false);
        ui->label_Nucleotides->setVisible(false);
        ui->label_DNA->setVisible(false);
        ui->label_RNA->setVisible(false);
        ui->lineEdit_DNA->setVisible(false);
        ui->lineEdit_RNA->setVisible(false);
        ui->label_carb->setVisible(false);
        ui->lineEdit_carb->setVisible(false);
        ui->label_PDB->setVisible(false);
        ui->lineEdit_PDB->setVisible(false);
        ui->label_fasta->setVisible(false);
        ui->lineEdit_fasta->setVisible(false);
        ui->pushButton_browseFasta->setVisible(false);
        ui->label_pConc->setVisible(false);
        ui->lineEdit_pConc->setVisible(false);
        ui->label_CIF->setVisible(true);
        ui->lineEdit_CIF->setVisible(true);
        ui->pushButton_CIF->setVisible(true);
    }
}

void MainWindow::on_pushButton_solventConc_clicked()
{
    QString El = ui->lineEdit_Elsolvent->text();
    QString Num = ui->lineEdit_Elconc->text();
    QString toAdd = El + " " + Num;
    ui->textEdit_solventConc->append(toAdd);
}

void MainWindow::on_pushButton_browseFasta_clicked()
{
    QString file_name = QFileDialog::getOpenFileName(this, "Open a file", QDir::homePath());
    ui->lineEdit_fasta->setText(file_name);
}

void MainWindow::on_pushButton_CIF_clicked()
{
    QString file_name = QFileDialog::getOpenFileName(this, "Open a file", QDir::homePath());
    ui->lineEdit_CIF->setText(file_name);
}

void MainWindow::receiveData(QString anglePIn, QString angleLIn, QString peEscapeIn, QString flEscapeIn, QString calcSurrIn, QString defineDensityIn, QString densityIn,
                             QString surrElementsIn, QString goniometerIn, QString polaristionIn, QString containerTypeIn, QString containerThicknessIn,
                             QString containerDensityIn, QString matieralMixtureIn, QString materialElementsIn){
   angleP = anglePIn;
   angleL = angleLIn;
   peEscape = peEscapeIn;
           flEscape = flEscapeIn;
           calcSurr = calcSurrIn;
           defineDensity = defineDensityIn;
           density = densityIn;
           surrElements = surrElementsIn;
           goniometer = goniometerIn;
           polaristion = polaristionIn;
           containerType = containerTypeIn;
           containerThickness = containerThicknessIn;
           containerDensity = containerDensityIn;
           matieralMixture = matieralMixtureIn;
           materialElements = materialElementsIn;
  // ui->lineEdit_X->setText(angleP);
}

void MainWindow::on_pushButton_crystalAdvanced_clicked()
{
    //CrystalAdvanced crystaladvanced;
    //crystaladvanced.setModal(true);
    //crystaladvanced.exec();
    crystaladvanced = new CrystalAdvanced(this);
    if (density == ""){
        defineDensity = "No";
    }
    else{
        defineDensity = "Yes";
    }
    crystaladvanced->updateData(angleP, angleL, peEscape, flEscape, calcSurr, defineDensity, density, surrElements, goniometer, polaristion,
                                containerType, containerThickness, containerDensity, matieralMixture, materialElements);
    //crystaladvanced->clearSurroundingEl();
    //crystaladvanced->clearMaterialEl();
    if (surrElements != ""){
      QString surr = "SURROUNDINGHEAVYCONC";
      std::string utf8_surr = surrElements.toUtf8().constData();
      tokenize2(utf8_surr, " ", surr);
    }
    //crystaladvanced->appendSurroundingEl(surrElements);
    if (materialElements != ""){
      QString material = "MATERIALELEMENTS";
      std::string utf8_material = materialElements.toUtf8().constData();
      tokenize2(utf8_material, " ", material);
    }
    //crystaladvanced->appendMaterialEl(materialElements);
    connect(crystaladvanced,SIGNAL(sendData(QString, QString, QString, QString, QString, QString, QString, QString, QString, QString, QString, QString, QString, QString, QString)),
            this, SLOT(receiveData(QString, QString, QString, QString, QString, QString, QString, QString, QString, QString, QString, QString, QString, QString, QString)));
    crystaladvanced->setWindowModality(Qt::ApplicationModal);
    crystaladvanced->show();

}

void MainWindow::writeInput(QString filename){ //this writes the RADDOSE-3D input file
    std::ofstream outfile;
    QString line1 = "##############################################################################";
    QString line2 = "#                               Crystal Block                                #";
    QString line3 = "##############################################################################";
    //need to convert QString to other format
    /*
            containerType = containerTypeIn;
            containerThickness = containerThicknessIn;
            containerDensity = containerDensityIn;
            matieralMixture = matieralMixtureIn;
            materialElements = materialElementsIn;
*/

    //QString inputFile = "MyInput_GUI.txt";
    QString inputFile = filename;
    QByteArray ba = inputFile.toLocal8Bit();
    const char *inputFileName = ba.data();
    outfile.open(inputFileName);
    outfile << line1.toUtf8().constData() << std::endl;
    outfile << line2.toUtf8().constData() << std::endl;
    outfile << line3.toUtf8().constData() << std::endl;
    outfile << "Crystal" << std::endl;
    outfile << "Type " << crystalType.toUtf8().constData() << std::endl;
    if (ppm != ""){
        outfile << "PixelsPerMicron " << ppm.toUtf8().constData() << std::endl;
    }
    else{
        QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must set pixels per micron"));
        return;
    }
    if (crystalType == "Cuboid"){
        if (dimX != "" && dimY != "" && dimZ != ""){
            outfile << "Dimensions " << dimX.toUtf8().constData() << " " << dimY.toUtf8().constData() << " " << dimZ.toUtf8().constData() << std::endl;
        }
        else{
            QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must set XYZ dimensions for a cuboid crystal"));
            return;
        }
    }
    else if(crystalType == "Cylinder"){
        if (dimX != "" && dimY != ""){
            outfile << "Dimensions " << dimX.toUtf8().constData() << " " << dimY.toUtf8().constData() << std::endl;
        }
        else{
            QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must set H and D dimensions for a cylindrical crystal"));
            return;
        }
    }
    else if(crystalType == "Spherical"){
        if (dimX != ""){
            outfile << "Dimensions " << dimX.toUtf8().constData() << std::endl;
        }
        else{
            QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must set diameter for a spherical crystal"));
            return;
        }
    }
    else if(crystalType == "Polyhedron"){
        if (modelFile != ""){
            outfile << "WIREFRAMETYPE OBJ" << std::endl;
            outfile << "ModelFile " << modelFile.toUtf8().constData() << std::endl;
        }
        else{
            QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify .obj model file for a polyhedron crystal"));
            return;
        }
    }

    outfile << "AbsCoefCalc " << absCoef.toUtf8().constData() << std::endl;
    if (absCoef == "RD3D" || absCoef == "SAXS"){
        if (cellA != ""  && cellB != "" && cellC != ""){
            outfile << "UNITCELL " << cellA.toUtf8().constData() << " " << cellB.toUtf8().constData() << " " << cellC.toUtf8().constData()
                    << " " << cellAlpha.toUtf8().constData() << " " << cellBeta.toUtf8().constData() << " " << cellGamma.toUtf8().constData() << std::endl;
        }
        else{
            QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify Unit cell dimensions for RD3D AbsCoef"));
            return;
        }
        if (absCoef == "RD3D"){
            if (numMonmomer != ""){
                outfile << "NumMonomers " << numMonmomer.toUtf8().constData() << std::endl;
            }
            else{
                QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify number of monomers for RD3D AbsCoef"));
                return;
            }
        }
        else if (absCoef == "SAXS"){
            if (pConc != ""){
                outfile << "PROTEINCONC " << pConc.toUtf8().constData() << std::endl;
            }
            else{
                QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify protein concentration for SAXS AbsCoef"));
                return;
            }
        }
        if (numResidue != ""){
            outfile << "NumResidues " << numResidue.toUtf8().constData() << std::endl;
        }
        else{
            QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify number of residues for RD3D AbsCoef"));
            return;
        }
        if (heavyProt != ""){
            outfile << "ProteinHeavyAtoms " << heavyProt.toUtf8().constData() << std::endl;
        }
        if (solventConc != ""){
            outfile << "SolventHeavyConc " << solventConc.toUtf8().constData() << std::endl;
        }
        if (solventFrac != ""){
            outfile << "SolventFraction " << solventFrac.toUtf8().constData() << std::endl;
        }
        if (numDNA != ""){
            outfile << "numDNA " << numDNA.toUtf8().constData() << std::endl;
        }
        if (numRNA != ""){
            outfile << "numRNA " << numRNA.toUtf8().constData() << std::endl;
        }
        if (numCarb != ""){
            outfile << "numCarb " << numCarb.toUtf8().constData() << std::endl;
        }
    }
    if (absCoef == "EXP"){
        if (pdb != ""){
            outfile << "PDB " << pdb.toUtf8().constData() << std::endl;
        }
        else{
            QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify PDB code for EXP AbsCoef"));
            return;
        }
        if (solventConc != ""){
            outfile << "SolventHeavyConc " << solventConc.toUtf8().constData() << std::endl;
        }
    }
    if (absCoef == "SEQUENCE" || absCoef == "SAXSSEQ"){
        if (cellA != ""  && cellB != "" && cellC != ""){
            outfile << "UNITCELL " << cellA.toUtf8().constData() << " " << cellB.toUtf8().constData() << " " << cellC.toUtf8().constData()
                    << " " << cellAlpha.toUtf8().constData() << " " << cellBeta.toUtf8().constData() << " " << cellGamma.toUtf8().constData() << std::endl;
        }
        else{
            QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify Unit cell dimensions for SEQUENCE AbsCoef"));
            return;
        }
        if (absCoef == "SEQUENCE"){
            if (numMonmomer != ""){
                outfile << "NumMonomers " << numMonmomer.toUtf8().constData() << std::endl;
            }
            else{
                QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify number of monomers for SEQUENCE AbsCoef"));
                return;
            }
        }
        else if (absCoef == "SAXSSEQ"){
            if (pConc != ""){
                outfile << "PROTEINCONC " << pConc.toUtf8().constData() << std::endl;
            }
            else{
                QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify protein concentration for SAXSSEQ AbsCoef"));
                return;
            }
        }
        else{
            QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify number of monomers for SEQUENCE AbsCoef"));
            return;
        }
        if (fasta != ""){
            outfile << "SEQFILE. " << fasta.toUtf8().constData() << std::endl;
        }
        else{
            QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify the sequence file for SEQUENCE AbsCoef"));
            return;
        }
        if (solventConc != ""){
            outfile << "SolventHeavyConc " << solventConc.toUtf8().constData() << std::endl;
        }
        if (solventFrac != ""){
            outfile << "SolventFraction " << solventFrac.toUtf8().constData() << std::endl;
        }
    }
    if (absCoef == "SMALLMOLE"){
        if (cellA != ""  && cellB != "" && cellC != ""){
            outfile << "UNITCELL " << cellA.toUtf8().constData() << " " << cellB.toUtf8().constData() << " " << cellC.toUtf8().constData()
                    << " " << cellAlpha.toUtf8().constData() << " " << cellBeta.toUtf8().constData() << " " << cellGamma.toUtf8().constData() << std::endl;
        }
        else{
            QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify Unit cell dimensions for SMALLMOLE AbsCoef"));
            return;
        }
        if (numMonmomer != ""){
            outfile << "NumMonomers " << numMonmomer.toUtf8().constData() << std::endl;
        }
        else{
            QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify number of monomers for SMALLMOLE AbsCoef"));
            return;
        }
        if (heavyProt != ""){
            outfile << "SMALLMOLEATOMS " << heavyProt.toUtf8().constData() << std::endl;
        }
        else{
            QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify the atoms for SMALLMOLE AbsCoef"));
            return;
        }

    }

    if (absCoef == "CIF"){
        if (cif != ""){
            outfile << "CIF " << cif.toUtf8().constData() << std::endl;
        }
        else{
            QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify CIF file for CIF AbsCoef"));
            return;
        }
    }

    if (subprogram == "XFEL" || subprogram == "MonteCarlo"){
        outfile << "Subprogram " << subprogram.toUtf8().constData() << std::endl;
        if (photons != ""){
            outfile << "SIMPHOTONS " << photons.toUtf8().constData() << std::endl;
        }
        else{
            QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify the number of photons to simulate for Monte Carlo and XFEL subprograms"));
            return;
        }
        if (runs != ""){
            outfile << "Runs " << runs.toUtf8().constData() << std::endl;
        }
        else{
            QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify the number of runs for Monte Carlo and XFEL subprograms"));
            return;
        }
    }

    if (angleP != ""){
        outfile << "AngleP " << angleP.toUtf8().constData() << std::endl;
    }
    if (angleL != ""){
        outfile << "AngleL " << angleL.toUtf8().constData() << std::endl;
    }
    if (peEscape == "True"){
        outfile << "CALCULATEPEESCAPE " << peEscape.toUtf8().constData() << std::endl;
        QString horizontal = "0";
        QString vertical = "90";
        if (goniometer == "Horizontal"){
            outfile << "GONIOMETERAXIS " << horizontal.toUtf8().constData() << std::endl;
        }
        else if (goniometer == "Vertical"){
            outfile << "GONIOMETERAXIS " << vertical.toUtf8().constData() << std::endl;
        }
        if (polaristion == "Horizontal"){
            outfile << "POLARISATIONDIRECTION " << horizontal.toUtf8().constData() << std::endl;
        }
        else if (polaristion == "Vertical"){
            outfile << "POLARISATIONDIRECTION " << vertical.toUtf8().constData() << std::endl;
        }
    }
    if (flEscape == "True"){
        outfile << "CALCULATEFLESCAPE " << flEscape.toUtf8().constData() << std::endl;
    }
    if (calcSurr == "True"){
        outfile << "CALCSURROUNDING " << calcSurr.toUtf8().constData() << std::endl;
        if (defineDensity == "Yes"){
            QString truth = "True";
            outfile << "DENSITYBASED " << truth.toUtf8().constData() << std::endl;
            if (density != ""){
                outfile << "SURROUNDINGDENSITY " << density.toUtf8().constData() << std::endl;
            }
            if (surrElements != ""){
                outfile << "SURROUNDINGELEMENTS " << surrElements.toUtf8().constData() << std::endl;
            }
        }
        else{
            if (surrElements != ""){
                outfile << "SURROUNDINGHEAVYCONC " << surrElements.toUtf8().constData() << std::endl;
            }
        }
    }
    if (containerType == "Mixture" || containerType == "Elements"){
        outfile << "CONTAINERMATERIALTYPE " << containerType.toUtf8().constData() << std::endl;
        if (containerThickness != ""){
            outfile << "CONTAINERTHICKNESS " << containerThickness.toUtf8().constData() << std::endl;
        }
        else{
            QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify the container thickness"));
            return;
        }
        if (containerDensity != ""){
            outfile << "CONTAINERDENSITY " << containerDensity.toUtf8().constData() << std::endl;
        }
        else{
            QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify the container density"));
            return;
        }
        if (containerType == "Mixture"){
            if (matieralMixture != ""){
                outfile << "MATERIALMIXTURE " << matieralMixture.toUtf8().constData() << std::endl;
            }
            else{
                QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify the container mixture"));
                return;
            }
        }
        if (containerType == "Elements"){
            if (materialElements != ""){
                outfile << "MATERIALELEMENTS " << materialElements.toUtf8().constData() << std::endl;
            }
            else{
                QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify the container elements"));
                return;
            }
        }
    }


    //now I want to write the beam stuff
    //adding multiple beams and wedges will need to be a separate option so just write one file for now
    line2 = "#                               Beam Block                                   #";
    outfile << line1.toUtf8().constData() << std::endl;
    outfile << line2.toUtf8().constData() << std::endl;
    outfile << line3.toUtf8().constData() << std::endl;
    outfile << "Beam" << std::endl;
    outfile << "Type " << beamType.toUtf8().constData() << std::endl;
    if (beamType == "Gaussian"){
        if (beamFWHMX != "" && beamFWHMY != ""){
            outfile << "FWHM " << beamFWHMX.toUtf8().constData() << " " << beamFWHMY.toUtf8().constData()<< std::endl;
        }
        else{
            QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify the FWHM of a Gaussian beam"));
            return;
        }
    }
    if (beamType == "Experimental"){
        if (beamFile != ""){
            outfile << "File " << beamFile.toUtf8().constData() << std::endl;
        }
        else{
            QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must provide the file of an experimental beam"));
            return;
        }
        if (beamPxSizeX != "" && beamPxSizeY != ""){
            outfile << "PIXELSIZE " << beamPxSizeX.toUtf8().constData() << " " << beamPxSizeY.toUtf8().constData()<<std::endl;
        }
        else{
            QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must provide the pixel size of an experimental beam"));
            return;
        }
    }
    else{ //gaussian or topHat
        if (beamCollType != "None"){
            if (beamCollX != "" && beamCollY != ""){
                outfile << "Collimation " << beamCollType.toUtf8().constData() << " " << beamCollX.toUtf8().constData() << " " << beamCollY.toUtf8().constData() << std::endl;
            }
            else{
                if (beamType == "TopHat"){
                    QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify collimation of a TopHat beam"));
                    return;
                }
            }
        }
        else{
            if (beamType == "TopHat"){
                QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify collimation of a TopHat beam"));
                return;
            }
        }
    }
    if (subprogram == "XFEL"){
        if (beamFlux != ""){
            outfile << "PULSEENERGY " << beamFlux.toUtf8().constData() << std::endl;
        }
        else{
            QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify pulse energy of an XFEL"));
            return;
        }
    }
    else if(subprogram == "EMED"){
        if (beamFlux != ""){
            outfile << "EXPOSURE " << beamFlux.toUtf8().constData() << std::endl;
        }
        else{
            QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify the total exposure"));
            return;
        }
    }
    else{
        if (beamFlux != ""){
            outfile << "FLUX " << beamFlux.toUtf8().constData() << std::endl;
        }
        else{
            QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify the beam flux"));
            return;
        }
    }
    if (beamEnergy != ""){
        outfile << "ENERGY " << beamEnergy.toUtf8().constData() << std::endl;
    }
    else{
        QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify the beam energy"));
        return;
    }
    if (beamIsMon == "No"){
        if (beamEnergyFWHM != ""){
            outfile << "ENERGYFWHM " << beamEnergyFWHM.toUtf8().constData() << std::endl;
        }
        else{
            QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify the beam energy spread for a polychromatic beam"));
            return;
        }
    }

    //and now the wedge
    line2 = "#                               Wedge Block                                  #";
    outfile << line1.toUtf8().constData() << std::endl;
    outfile << line2.toUtf8().constData() << std::endl;
    outfile << line3.toUtf8().constData() << std::endl;
    if (wedgeStart != "" && wedgeEnd != ""){
        outfile << "Wedge " << wedgeStart.toUtf8().constData() << " " << wedgeEnd.toUtf8().constData() << std::endl;
    }
    else{
        QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify the wedge start and end angle"));
        return;
    }
    if (expTime != ""){
        outfile << "ExposureTime " << expTime.toUtf8().constData() << std::endl;
    }
    else{
        QMessageBox::critical(this, tr("RADDOSE-3D Input Error"), tr("Error! Must specify the wedge exposure time"));
        return;
    }
    if (angleRes != ""){
        outfile << "ANGULARRESOLUTION " << angleRes.toUtf8().constData() << std::endl;
    }
    if (offsetX != "" && offsetY != "" && offsetZ != ""){
        outfile << "STARTOFFSET " << offsetX.toUtf8().constData() << " " << offsetY.toUtf8().constData() << " " << offsetZ.toUtf8().constData() << std::endl;
    }
    if (translationX != "" && translationY != "" && translationZ != ""){
        outfile << "TRANSLATEPERDEGREE " << translationX.toUtf8().constData() << " " << translationY.toUtf8().constData() << " " << translationZ.toUtf8().constData() << std::endl;
    }
    if (offsetRot != ""){
        outfile << "ROTAXBEAMOFFSET " << offsetRot.toUtf8().constData() << std::endl;
    }

    outfile.close();


   // runWindow test;
   // test.runRADDOSE();
    /*
    proc = new QProcess(this);
    QString command = "java";
    QStringList arguments;
    arguments << "-jar" << "raddose3d.jar" << "-i" << inputFile;
    proc -> start(command,arguments);
    connect(proc, SIGNAL(readyReadStandardOutput()), this,SLOT(rightMessage()));
    connect(proc, SIGNAL(readyReadStandardError()), this,SLOT(wrongMessage()));
*/
}
/*
void MainWindow::rightMessage(){
    QByteArray strdata = proc->readAllStandardOutput();
}

void MainWindow::wrongMessage(){
    QByteArray strdata = proc->readAllStandardError();
}
*/

void MainWindow::saveValues(){
    //just set all the variables irrespective of chosen things
    //Then I can choose whether to write them to file
        std::regex newlines_re("\n+");


        crystalType = ui->comboBox->currentText();
        ppm = ui->lineEdit_ppm->text();
        absCoef = ui->comboBox_AbsCoef->currentText();

        dimX = ui->lineEdit_X->text();
        dimY = ui->lineEdit_Y->text();
        dimZ = ui->lineEdit_Z->text();
        modelFile = ui->lineEdit_modelfile->text();

        cellA = ui->lineEdit_a->text();
        cellB = ui->lineEdit_b->text();
        cellC = ui->lineEdit_c->text();
        cellAlpha = ui->lineEdit_alpha->text();
        cellBeta = ui->lineEdit_beta->text();
        cellGamma = ui->lineEdit_gamma->text();
        numMonmomer = ui->lineEdit_Monomers->text();
        numResidue = ui->lineEdit_Residues->text();
        heavyProt = ui->textEdit_heavyProt->toPlainText();
        heavyProt = QString::fromStdString(std::regex_replace(heavyProt.toUtf8().constData(), newlines_re, " "));
        solventConc = ui->textEdit_solventConc->toPlainText();
        solventConc = QString::fromStdString(std::regex_replace(solventConc.toUtf8().constData(), newlines_re, " "));
        solventFrac = ui->lineEdit_solventFr->text();
        numDNA = ui->lineEdit_DNA->text();
        numRNA= ui->lineEdit_RNA->text();
        numCarb = ui->lineEdit_carb->text();

        pdb = ui->lineEdit_PDB->text();
        cif = ui->lineEdit_CIF->text();
        fasta = ui->lineEdit_fasta->text();
        pConc = ui->lineEdit_pConc->text();

        subprogram = ui->comboBox_subprogram->currentText();
        photons = ui->lineEdit_photons->text();
        runs = ui->lineEdit_runs->text();


        //now the beam
        beamType = ui->comboBox_beamType->currentText();
        beamFWHMX = ui->lineEdit_FWHMX->text();
        beamFWHMY = ui->lineEdit_FWHMY->text();
        beamFile = ui->lineEdit_beamFile->text();
        beamPxSizeX = ui->lineEdit_pxSizeX->text();
        beamPxSizeY = ui->lineEdit_pxSizeX->text();
        beamCollType = ui->comboBox_collType->currentText();
        beamCollX = ui->lineEdit_collX->text();
        beamCollY = ui->lineEdit_collY->text();
        beamFlux = ui->lineEdit_flux->text();
        beamEnergy = ui->lineEdit_beamEn->text();
        beamEnergyFWHM = ui->lineEdit_beamEnFWHM->text();
        beamIsMon = ui->comboBox_monochrome->currentText();

        //now the wedge
         wedgeStart = ui->lineEdit_startAngle->text();
         wedgeEnd = ui->lineEdit_endAngle->text();
         expTime=ui->lineEdit_exposureTime->text();
         angleRes = ui->lineEdit_angleRes->text();
         offsetX = ui->lineEdit_offsetX->text();
         offsetY = ui->lineEdit_offsetY->text();
         offsetZ = ui->lineEdit_offsetZ->text();
         offsetRot = ui->lineEdit_rotOffset->text();
         translationX = ui->lineEdit_tnsX->text();
         translationY = ui->lineEdit_tnsY->text();
         translationZ = ui->lineEdit_tnsZ->text();
}

void MainWindow::on_pushButton_run_clicked()
{
    saveValues();

    //next I need to write the input file
    writeInput("MyInput_GUI.txt");

    //and then run the program
    runwindow = new runWindow(this);
   // runwindow->setWindowModality(Qt::ApplicationModal);
    runwindow->show();
    runwindow->runRADDOSE();
}

void MainWindow::on_comboBox_subprogram_activated(const QString &arg1)
{
    std::string utf8_arg1 = arg1.toUtf8().constData();
    std::transform(utf8_arg1.begin(), utf8_arg1.end(),utf8_arg1.begin(), ::toupper);
    QString arg1_upper = QString::fromStdString(utf8_arg1);
    //i'll need to change the beam input here as well
    if (arg1_upper == "Standard RADDOSE-3D" || arg1_upper == "STANDARD RADDOSE-3D" ){
        ui->comboBox_subprogram->setCurrentText("Standard RADDOSE-3D");
        ui->label_photons->setVisible(false);
        ui->lineEdit_photons->setVisible(false);
        ui->lineEdit_photons->setText("");
        ui->label_runs->setVisible(false);
        ui->lineEdit_runs->setVisible(false);
        ui->lineEdit_runs->setText("");
        simulation = false;
        ui->label_pulseEn->setVisible(false);
        ui->lineEdit_pulseEn->setVisible(false);
        ui->label_exposure->setVisible(false);
        ui->lineEdit_exposure->setVisible(false);
        ui->label_flux->setVisible(true);
        ui->lineEdit_flux->setVisible(true);

       // ui->label_pulseLength->setVisible(false);
        ui->label_expTime->setVisible(true);
        ui->label_expTime->setText("Exposure time (s)");
    }
    else if (arg1_upper == "XFEL"){
        ui->label_photons->setVisible(true);
        ui->lineEdit_photons->setVisible(true);
        ui->label_runs->setVisible(true);
        ui->lineEdit_runs->setVisible(true);
        simulation = true;
        ui->label_pulseEn->setVisible(true);
        ui->lineEdit_pulseEn->setVisible(true);
        ui->label_flux->setVisible(false);
        ui->lineEdit_flux->setVisible(false);
        ui->label_exposure->setVisible(false);
        ui->lineEdit_exposure->setVisible(false);

       // ui->label_pulseLength->setVisible(true);
        ui->label_expTime->setVisible(true);
        ui->label_expTime->setText("Pulse Length (fs)");
    }
    else if (arg1_upper == "MonteCarlo" || arg1_upper == "MONTECARLO"){
        ui->comboBox_subprogram->setCurrentText("MonteCarlo");
        ui->label_photons->setVisible(true);
        ui->lineEdit_photons->setVisible(true);
        ui->label_runs->setVisible(true);
        ui->lineEdit_runs->setVisible(true);
        simulation = true;
        ui->label_pulseEn->setVisible(false);
        ui->lineEdit_pulseEn->setVisible(false);
        ui->label_flux->setVisible(true);
        ui->lineEdit_flux->setVisible(true);
        ui->label_exposure->setVisible(false);
        ui->lineEdit_exposure->setVisible(false);
        ui->label_flux->setText("Flux");
        //ui->label_pulseLength->setVisible(false);
        ui->label_expTime->setVisible(true);
        ui->label_expTime->setText("Exposure time (s)");
    }
    else if (arg1_upper == "EMED"){
        ui->comboBox_subprogram->setCurrentText("Standard RADDOSE-3D");
        ui->label_photons->setVisible(false);
        ui->lineEdit_photons->setVisible(false);
        ui->lineEdit_photons->setText("");
        ui->label_runs->setVisible(false);
        ui->lineEdit_runs->setVisible(false);
        ui->lineEdit_runs->setText("");
        simulation = false;
        ui->label_pulseEn->setVisible(false);
        ui->lineEdit_pulseEn->setVisible(false);
        ui->label_exposure->setVisible(true);
        ui->lineEdit_exposure->setVisible(true);
        ui->label_flux->setVisible(false);
        ui->lineEdit_flux->setVisible(false);

       // ui->label_pulseLength->setVisible(false);
        ui->label_expTime->setVisible(true);
        ui->label_expTime->setText("Exposure time (s)");
    }
}

//beam stuff
void MainWindow::on_comboBox_beamType_activated(const QString &arg1)
{
    std::string utf8_arg1 = arg1.toUtf8().constData();
    std::transform(utf8_arg1.begin(), utf8_arg1.end(),utf8_arg1.begin(), ::toupper);
    QString arg1_upper = QString::fromStdString(utf8_arg1);
    if (arg1_upper == "Gaussian" || arg1_upper == "GAUSSIAN"){
        //still need to add in collimation visibility
        ui->comboBox_beamType->setCurrentText("Gaussian");
        ui->label_FWHM->setVisible(true);
        ui->lineEdit_FWHMX->setVisible(true);
        ui->lineEdit_FWHMY->setVisible(true);
        ui->label_FWHMX->setVisible(true);
        ui->label_FWHMY->setVisible(true);

        ui->label_Collimation->setVisible(true);
        ui->label_collType->setVisible(true);
        ui->label_collDims->setVisible(true);
        ui->label_collX->setVisible(true);
        ui->label_collY->setVisible(true);
        ui->lineEdit_collX->setVisible(true);
        ui->lineEdit_collY->setVisible(true);
        ui->comboBox_collType->setVisible(true);

        ui->label_beamFile->setVisible(false);
        ui->lineEdit_beamFile->setVisible(false);
        ui->pushButton_beamFile->setVisible(false);
        ui->label_pxSize->setVisible(false);
        ui->lineEdit_pxSizeX->setVisible(false);
        ui->lineEdit_pxSizeY->setVisible(false);
        ui->label_pxX->setVisible(false);
        ui->label_pxY->setVisible(false);
    }
    else if (arg1_upper == "TopHat" || arg1_upper == "TOPHAT"){
        ui->comboBox_beamType->setCurrentText("TopHat");
        ui->label_FWHM->setVisible(false);
        ui->lineEdit_FWHMX->setVisible(false);
        ui->lineEdit_FWHMY->setVisible(false);
        ui->label_FWHMX->setVisible(false);
        ui->label_FWHMY->setVisible(false);
        ui->label_Collimation->setVisible(true);
        ui->label_collType->setVisible(true);
        ui->label_collDims->setVisible(true);
        ui->label_collX->setVisible(true);
        ui->label_collY->setVisible(true);
        ui->lineEdit_collX->setVisible(true);
        ui->lineEdit_collY->setVisible(true);
        ui->comboBox_collType->setVisible(true);
        ui->label_beamFile->setVisible(false);
        ui->lineEdit_beamFile->setVisible(false);
        ui->pushButton_beamFile->setVisible(false);
        ui->label_pxSize->setVisible(false);
        ui->lineEdit_pxSizeX->setVisible(false);
        ui->lineEdit_pxSizeY->setVisible(false);
        ui->label_pxX->setVisible(false);
        ui->label_pxY->setVisible(false);
    }
    else if (arg1_upper == "Experimental" || arg1_upper == "EXPERIMENTAL"){
        ui->comboBox_beamType->setCurrentText("Experimental");
        ui->label_FWHM->setVisible(false);
        ui->lineEdit_FWHMX->setVisible(false);
        ui->lineEdit_FWHMY->setVisible(false);
        ui->label_FWHMX->setVisible(false);
        ui->label_FWHMY->setVisible(false);
        ui->label_Collimation->setVisible(false);
        ui->label_collType->setVisible(false);
        ui->label_collDims->setVisible(false);
        ui->label_collX->setVisible(false);
        ui->label_collY->setVisible(false);
        ui->lineEdit_collX->setVisible(false);
        ui->lineEdit_collY->setVisible(false);
        ui->comboBox_collType->setVisible(false);
        ui->label_beamFile->setVisible(true);
        ui->lineEdit_beamFile->setVisible(true);
        ui->pushButton_beamFile->setVisible(true);
        ui->label_pxSize->setVisible(true);
        ui->lineEdit_pxSizeX->setVisible(true);
        ui->lineEdit_pxSizeY->setVisible(true);
        ui->label_pxX->setVisible(true);
        ui->label_pxY->setVisible(true);
    }
}

void MainWindow::on_comboBox_monochrome_activated(const QString &arg1)
{
    QPalette *palette = new QPalette();
    std::string utf8_arg1 = arg1.toUtf8().constData();
    std::transform(utf8_arg1.begin(), utf8_arg1.end(),utf8_arg1.begin(), ::toupper);
    QString arg1_upper = QString::fromStdString(utf8_arg1);
    if(arg1_upper == "Yes" || arg1_upper == "YES"){
        ui->comboBox_monochrome->setCurrentText("Yes");
        ui->lineEdit_beamEnFWHM->setReadOnly(true);
        palette->setColor(QPalette::Base,Qt::gray);
        ui->lineEdit_beamEnFWHM->setPalette(*palette);
    }
    else if (arg1_upper == "No" || arg1_upper == "NO"){
        ui->comboBox_monochrome->setCurrentText("No");
        ui->lineEdit_beamEnFWHM->setReadOnly(false);
        palette->setColor(QPalette::Base,Qt::black);
        ui->lineEdit_beamEnFWHM->setPalette(*palette);
    }
}

void MainWindow::on_pushButton_manualEdit_clicked()
{
    saveValues();
    //write input file
    writeInput("MyInput_GUI.txt");
    inputedit = new inputEdit(this);
    inputedit->setWindowModality(Qt::ApplicationModal);
    inputedit->show();
    inputedit->showInput();


}

void MainWindow::on_actionSave_triggered()
{
    saveValues();
    //ask to save
    QString savefile = QFileDialog::getSaveFileName(this, "Save a file",
    QDir::homePath());
    writeInput(savefile);
}

void MainWindow::populateParam(std::string input){

 //
}

void MainWindow::tokenize2(std::string s, std::string del, QString first)
{
    // crystaladvanced2 = new CrystalAdvanced(this);
    QString surr, material;
    int start = 0;
    int end = s.find(del);
    std::vector<std::string> theLine;
    while (end != -1) {
        theLine.push_back(s.substr(start, end - start));
        start = end + del.size();
        end = s.find(del, start);
    }
    theLine.push_back(s.substr(start, end - start));
    if (first == "SURROUNDINGHEAVYCONC" || first == "SURROUNDINGELEMENTS"){
        int size = theLine.size();
        //crystaladvanced2->clearSurroundingEl();
        for(int i = 0; i<size; i+=2){
            surr = "";
            surr = surr + QString::fromStdString(theLine[i]) + " " + QString::fromStdString(theLine[i+1]);
            crystaladvanced->appendSurroundingEl(surr);
        }
    }
    if (first == "MATERIALELEMENTS"){
        int size = theLine.size();
        //crystaladvanced2->clearMaterialEl();
        for(int i = 0; i<size; i+=2){
            material = "";
            material = material + QString::fromStdString(theLine[i]) + " " + QString::fromStdString(theLine[i+1]);
            crystaladvanced->appendMaterialEl(material);
        }
    }
}

void MainWindow::tokenize(std::string s, std::string del = " ")
{
    int start = 0;
    int end = s.find(del);
    std::vector<std::string> theLine;
    std::string first, second;
    while (end != -1) {
        theLine.push_back(s.substr(start, end - start));
        start = end + del.size();
        end = s.find(del, start);
    }
    theLine.push_back(s.substr(start, end - start));
    first = theLine[0];
    std::transform(first.begin(), first.end(),first.begin(), ::toupper);
    if (first == "TYPE"){
        second = theLine[1];
        std::transform(second.begin(), second.end(),second.begin(), ::toupper);
        if (second == "CUBOID" || second =="SPHERICAL" || second == "CYLINDER"|| second == "POLYHEDRON"){
            //this is the crystal type
            crystalType = QString::fromStdString(theLine[1]);
            ui->comboBox->setCurrentText(crystalType);
            on_comboBox_activated(crystalType);
        }
        if (second == "GAUSSIAN" || second =="TOPHAT" || second == "EXPERIMENTAL"){
            //this is the beam type
            beamType = QString::fromStdString(theLine[1]);
            ui->comboBox_beamType->setCurrentText(beamType);
            on_comboBox_beamType_activated(beamType);
        }
    }
    if (first == "MODELFILE"){
        modelFile = QString::fromStdString(theLine[1]);
        ui->lineEdit_modelfile->setText(modelFile);
    }
    if (first == "PIXELSPERMICRON"){
        ppm = QString::fromStdString(theLine[1]);
        ui->lineEdit_ppm->setText(ppm);
     }
    if (first == "ABSCOEFCALC"){
        absCoef = QString::fromStdString(theLine[1]);
        ui->comboBox_AbsCoef->setCurrentText(absCoef);
        on_comboBox_AbsCoef_activated(absCoef);
     }
    if (first == "NUMMONOMERS"){
        numMonmomer = QString::fromStdString(theLine[1]);
        ui->lineEdit_Monomers->setText(numMonmomer);
     }
    if (first == "NUMRESIDUES"){
        numResidue = QString::fromStdString(theLine[1]);
        ui->lineEdit_Residues->setText(numResidue);
     }
    if (first == "NUMRNA"){
        numRNA = QString::fromStdString(theLine[1]);
        ui->lineEdit_RNA->setText(numRNA);
     }
    if (first == "NUMDNA"){
        numDNA = QString::fromStdString(theLine[1]);
        ui->lineEdit_DNA->setText(numDNA);
     }
    if (first == "NUMCARB"){
        numCarb = QString::fromStdString(theLine[1]);
        ui->lineEdit_carb->setText(numCarb);
     }
    if (first == "SOLVENTFRACTION"){
        solventFrac = QString::fromStdString(theLine[1]);
        ui->lineEdit_solventFr->setText(solventFrac);
     }
    if (first == "DIMENSIONS"){
        dimX = QString::fromStdString(theLine[1]);
        ui->lineEdit_X->setText(dimX);
        int size = theLine.size();
        if (size > 2){
            dimY = QString::fromStdString(theLine[2]);
            ui->lineEdit_Y->setText(dimY);
        }
        if (size > 3){
            dimZ = QString::fromStdString(theLine[3]);
            ui->lineEdit_Z->setText(dimZ);
        }
    }
    if (first == "UNITCELL"){
        cellA = QString::fromStdString(theLine[1]);
        ui->lineEdit_a->setText(cellA);
        cellB = QString::fromStdString(theLine[2]);
        ui->lineEdit_b->setText(cellB);
        cellC = QString::fromStdString(theLine[3]);
        ui->lineEdit_c->setText(cellC);
        int size = theLine.size();
        if (size > 4){
            cellAlpha = QString::fromStdString(theLine[4]);
            ui->lineEdit_alpha->setText(cellAlpha);
            cellBeta = QString::fromStdString(theLine[5]);
            ui->lineEdit_beta->setText(cellBeta);
            cellGamma = QString::fromStdString(theLine[6]);
            ui->lineEdit_gamma->setText(cellGamma);
        }
    }
    if (first == "SUBPROGRAM"){
        subprogram = QString::fromStdString(theLine[1]);
        if (subprogram == "XFEL" || subprogram == "MONTECARLO"){
            loadSim = true;
        }
        else{
            loadSim = false;
        }
        ui->comboBox_subprogram->setCurrentText(subprogram);
        on_comboBox_subprogram_activated(subprogram);
    }
    if (first == "SIMPHOTONS"){
        photons = QString::fromStdString(theLine[1]);
        ui->lineEdit_photons->setText(photons);
    }
    if (first == "RUNS"){
        runs = QString::fromStdString(theLine[1]);
        ui->lineEdit_runs->setText(runs);
    }
    if (first == "PROTEINHEAVYATOMS"){
        int size = theLine.size();
        ui->textEdit_heavyProt->clear();
        for(int i = 1; i<size; i+=2){
            heavyProt = "";
            heavyProt = heavyProt + QString::fromStdString(theLine[i]) + " " + QString::fromStdString(theLine[i+1]);
            ui->textEdit_heavyProt->append(heavyProt);
        }
    }
    if (first == "SOLVENTHEAVYATOMS"){
        int size = theLine.size();
        ui->textEdit_solventConc->clear();
        for(int i = 1; i<size; i+=2){
            solventConc = "";
            solventConc = solventConc + QString::fromStdString(theLine[i]) + " " + QString::fromStdString(theLine[i+1]);
            ui->textEdit_solventConc->append(solventConc);
        }
    }
    if (first == "PDB"){
        pdb = QString::fromStdString(theLine[1]);
        ui->lineEdit_PDB->setText(pdb);
    }
    if (first == "SEQFILE"){
        fasta = QString::fromStdString(theLine[1]);
        ui->lineEdit_fasta->setText(fasta);
    }
    if (first == "PROTEINCONC"){
        pConc = QString::fromStdString(theLine[1]);
        ui->lineEdit_pConc->setText(pConc);
    }
    if (first == "CIF"){
        cif = QString::fromStdString(theLine[1]);
        ui->lineEdit_CIF->setText(cif);
    }
    //now advanced crystal
    if (first == "ANGLEP"){
        angleP = QString::fromStdString(theLine[1]);
    }
    if (first == "ANGLEL"){
        angleL = QString::fromStdString(theLine[1]);
    }
    if (first == "CALCULATEPEESCAPE"){
        peEscape = QString::fromStdString(theLine[1]);
        if (peEscape == "TRUE"){
            loadPE = true;
        }
        else{
            loadPE = false;
        }
    }
    if (first == "CALCULATEFLESCAPE"){
        flEscape = QString::fromStdString(theLine[1]);
        if (flEscape == "TRUE"){
            loadFL = true;
        }
        else{
            loadFL = false;
        }
    }
    if (first == "CALCSURROUNDING"){
        calcSurr = QString::fromStdString(theLine[1]);
        if (calcSurr == "TRUE"){
            loadSurr = true;
        }
        else{
            loadSurr = false;
        }
    }
    if (first == "DENSITY"){
        density = QString::fromStdString(theLine[1]);
        loadDensity = true;
        defineDensity = "Yes";
    }
    if (first == "GONIOMETERAXIS"){
        goniometer = QString::fromStdString(theLine[1]);
    }
    if (first == "POLARISATIONDIRECTION"){
        polaristion = QString::fromStdString(theLine[1]);
    }
    if (first == "POLARISATIONDIRECTION"){
        polaristion = QString::fromStdString(theLine[1]);
    }
    if(first == "CONTAINERMATERIALTYPE"){
        containerType = QString::fromStdString(theLine[1]);
        if (containerType == "MATERIAL" || containerType=="ELEMENTAL"){
            loadContainerType = true;
        }
        else{
            loadContainerType = false;
        }
    }
    if(first == "CONTAINERTHICKNESS"){
        containerThickness = QString::fromStdString(theLine[1]);
    }
    if(first == "CONTAINERDENSITY"){
        containerDensity = QString::fromStdString(theLine[1]);
    }
    if(first == "MATERIALMIXTURE"){
       matieralMixture = QString::fromStdString(theLine[1]);
    }
    if (first == "SURROUNDINGHEAVYCONC" || first == "SURROUNDINGELEMENTS"){
        int size = theLine.size();
        QString surr = surrElements;
        crystaladvanced->clearSurroundingEl();
        surrElements = "";
        for(int i = 1; i<size; i+=2){
            surr = "";
            surr = surr + QString::fromStdString(theLine[i]) + " " + QString::fromStdString(theLine[i+1]);
            surrElements = surrElements + surr + " ";
            crystaladvanced->appendSurroundingEl(surr);
        }

    }
    if (first == "MATERIALELEMENTS"){
        int size = theLine.size();
        QString material = materialElements;
        crystaladvanced->clearMaterialEl();
        materialElements = "";
        for(int i = 1; i<size; i+=2){
            material = "";
            material = material + QString::fromStdString(theLine[i]) + " " + QString::fromStdString(theLine[i+1]);
            materialElements = materialElements + material;
            crystaladvanced->appendMaterialEl(material);
        }
    }
    crystaladvanced = new CrystalAdvanced(this);
    crystaladvanced->updateData(angleP, angleL, peEscape, flEscape, calcSurr, defineDensity, density, surrElements, goniometer, polaristion,
                                containerType, containerThickness, containerDensity, matieralMixture, materialElements);


    //okay, now it's time to load the beam
    if (first == "ENERGY"){
        beamEnergy = QString::fromStdString(theLine[1]);
        ui->lineEdit_beamEn->setText(beamEnergy);
    }
    if (first == "FLUX"){
        beamFlux = QString::fromStdString(theLine[1]);
        ui->lineEdit_flux->setText(beamFlux);
    }
    if (first == "PULSEENERGY"){
        beamFlux = QString::fromStdString(theLine[1]);
        ui->lineEdit_flux->setText(beamFlux);
    }
    if (first == "FILE"){
        beamFile = QString::fromStdString(theLine[1]);
        ui->lineEdit_beamFile->setText(beamFile);
    }
    if (first == "ENERGYFWHM"){
        beamEnergyFWHM = QString::fromStdString(theLine[1]);
        ui->lineEdit_beamEnFWHM->setText(beamEnergyFWHM);
        beamIsMon = "No";
        on_comboBox_monochrome_activated(beamIsMon);
    }
    if (first == "FWHM"){
        beamFWHMX = QString::fromStdString(theLine[1]);
        ui->lineEdit_FWHMX->setText(beamFWHMX);
        beamFWHMY = QString::fromStdString(theLine[2]);
        ui->lineEdit_FWHMY->setText(beamFWHMY);
    }
    if (first == "COLLIMATION"){
        loadColl = true;
        beamCollType = QString::fromStdString(theLine[1]);
        ui->comboBox_collType->setCurrentText(beamCollType);
        beamCollX = QString::fromStdString(theLine[2]);
        ui->lineEdit_collX->setText(beamCollX);
        beamCollY = QString::fromStdString(theLine[3]);
        ui->lineEdit_collY->setText(beamCollY);
        on_comboBox_collType_activated(beamCollType);
    }
    if (first == "PIXELSIZE"){
        beamPxSizeX = QString::fromStdString(theLine[1]);
        ui->lineEdit_pxSizeX->setText(beamPxSizeX);
        beamPxSizeY = QString::fromStdString(theLine[2]);
        ui->lineEdit_pxSizeY->setText(beamPxSizeY);
    }

    //and now the wedge
    if (first == "WEDGE"){
        wedgeStart = QString::fromStdString(theLine[1]);
        ui->lineEdit_startAngle->setText(wedgeStart);
        wedgeEnd = QString::fromStdString(theLine[2]);
        ui->lineEdit_endAngle->setText(wedgeEnd);
    }
    if (first == "EXPOSURETIME"){
        expTime = QString::fromStdString(theLine[1]);
        ui->lineEdit_exposureTime->setText(expTime);
    }
    if (first == "ANGULARRESOLUTION"){
        angleRes = QString::fromStdString(theLine[1]);
        ui->lineEdit_angleRes->setText(angleRes);
    }
    if (first == "ROTAXBEAMOFFSET"){
        offsetRot = QString::fromStdString(theLine[1]);
        ui->lineEdit_rotOffset->setText(offsetRot);
    }
    if (first == "STARTOFFSET"){
        offsetX = QString::fromStdString(theLine[1]);
        ui->lineEdit_offsetX->setText(offsetX);
        offsetY = QString::fromStdString(theLine[2]);
        ui->lineEdit_offsetY->setText(offsetY);
        offsetZ = QString::fromStdString(theLine[3]);
        ui->lineEdit_offsetZ->setText(offsetZ);
    }
    if (first == "TRANSLATEPERDEGREE"){
        translationX = QString::fromStdString(theLine[1]);
        ui->lineEdit_tnsX->setText(translationX);
        translationY = QString::fromStdString(theLine[2]);
        ui->lineEdit_tnsY->setText(translationY);
        translationZ = QString::fromStdString(theLine[3]);
        ui->lineEdit_tnsZ->setText(translationZ);
    }
}

void MainWindow::on_actionOpen_triggered()
{
    /*
    QString openfile = QFileDialog::getOpenFileName(this, "Open a file",
    QDir::homePath());
   // writeInput(openfile);
    //read a file and populate textboxes
    std::string utf8_open = openfile.toUtf8().constData();
    std::ifstream infile(utf8_open);
   // QString openfile = "MyInput_GUI.txt";
    std::string line;
   // QByteArray ba = openfile.toLocal8Bit();
   // const char *openfileName = ba.data();
   // infile.open(openfileName);
    while (getline(infile, line)){
        tokenize(line, " ");
    }
    infile.close();
    */
}

void MainWindow::on_actionOpen_2_triggered()
{
    loadSim = false;
    loadColl = false;
    loadContainerType = false;
    loadFL = false;
    loadPE = false;
    loadSurr = false;
    loadDensity = false;
    QString openfile = QFileDialog::getOpenFileName(this, "Open a file",
    QDir::homePath());
   // writeInput(openfile);
    //read a file and populate textboxes
    std::string utf8_open = openfile.toUtf8().constData();
    std::ifstream infile(utf8_open);
   // QString openfile = "MyInput_GUI.txt";
    std::string line;
   // QByteArray ba = openfile.toLocal8Bit();
   // const char *openfileName = ba.data();
   // infile.open(openfileName);
    while (getline(infile, line)){
        tokenize(line, " ");
    }
    //change the subprogram if not there
    if (loadSim == false){
        subprogram = "Standard RADDOSE-3D";
        on_comboBox_subprogram_activated(subprogram);
    }
    if (loadColl == false){
        beamCollType = "None";
        on_comboBox_collType_activated(beamCollType);
    }
    if (loadContainerType == false){
        containerType = "None";
    }
    if (loadPE == false){
        peEscape = "False";
    }
    if (loadFL == false){
        flEscape = "False";
    }
    if (loadSurr == false){
        calcSurr = "False";
    }
    if (loadDensity == false){
        defineDensity = "No";
    }
   // crystaladvanced = new CrystalAdvanced(this);
   // crystaladvanced->updateData2(loadContainerType, loadPE, loadFL, loadSurr);

    infile.close();
}

void MainWindow::on_actionUser_guide_triggered()
{
    //Open the pdf
    //QDesktopServices::openUrl(QUrl("file://~/git/RADDOSE-3D/doc/user-guide.pdf"));

}

void MainWindow::on_actionClose_triggered()
{
    close();
}

void MainWindow::on_actionAbout_triggered()
{
    //QDesktopServices::openUrl(QUrl("file://output-Summary.csv"));
    QMessageBox::information(this, tr("RADDOSE-3D"), tr("If useful, please cite: \nRADDOSE-3D: time- and space-resolved modelling of dose in macromolecular crystallography "
                                                        "Zeldin, O. B.; Gerstel, M. & Garman, E. F. (2013). J. Appl. Cryst. 46 doi:10.1107/S0021889813011461\n"
                                                        "And also:\n"
                                                        "Estimate your dose: RADDOSE-3D Bury, C. S.; Brooks-Bartlett, J. C.; Walsh, S. P. & Garman, E. F. (2018). Pro-"
                                                        "tein Science 27 doi:10.1002/pro.3302\n\n"
                                                        "If you used the SAXS feature please cite:\n"
                                                        "Development of tools to automate quantitative analysis of radiation damage in SAXS experiments"
                                                        "Brooks-Bartlett, J. C.; Batters, R. A.; Bury, C. S.; Lowe, E. D.; Ginn, H. M.;"
                                                        "Round, A., & Garman, E. F. (2017). J. Synch. Rad. 24 doi:10.1107/S1600577516015083\n\n"
                                                        "For using the small molecule feature please cite:\n"
                                                        "Christensen, J., Horton, P. N., Bury, C. S., Dickerson, J. L., Taberman, H., Garman, E. F. & Coles, S. J. (2019). IUCrJ 6, 703-713.\n\n"
                                                        "If you found the XFEL subprogram useful please cite:\n"
                                                        "Dickerson, J. L., McCubbin, P. T. N. & Garman, E. F. (2020). J. Appl. Cryst. 53, 549-560.\n\n"
                                                        "And for the Monte Carlo subprogram please cite:\n"
                                                        "Dickerson, JL, Garman, EF. Doses for experiments with microbeams and microcrystals: Monte Carlo simulations in RADDOSE‐3D. Protein Science. 2021; 30: 8– 19. https://doi.org/10.1002/pro.3922\n\n"));
}

void MainWindow::on_comboBox_collType_activated(const QString &arg1)
{
    std::string utf8_arg1 = arg1.toUtf8().constData();
    std::transform(utf8_arg1.begin(), utf8_arg1.end(),utf8_arg1.begin(), ::toupper);
    QString arg1_upper = QString::fromStdString(utf8_arg1);
    //i'll need to change the beam input here as well
    if (arg1_upper == "None" || arg1_upper == "NONE" ){
        ui->comboBox_subprogram->setCurrentText("None");
        ui->label_collDims->setVisible(false);
        ui->label_collX->setVisible(false);
        ui->label_collY->setVisible(false);
        ui->lineEdit_collX->setVisible(false);
        ui->lineEdit_collY->setVisible(false);
    }
    else if (arg1_upper == "RECTANGULAR" || arg1_upper == "Rectangular"){
        ui->comboBox_subprogram->setCurrentText("Rectangular");
        ui->label_collDims->setVisible(true);
        ui->label_collX->setVisible(true);
        ui->label_collY->setVisible(true);
        ui->lineEdit_collX->setVisible(true);
        ui->lineEdit_collY->setVisible(true);
    }
    else if (arg1_upper == "CIRCULAR" || arg1_upper == "Circular"){
        ui->comboBox_subprogram->setCurrentText("Circular");
        ui->label_collDims->setVisible(true);
        ui->label_collX->setVisible(true);
        ui->label_collY->setVisible(true);
        ui->lineEdit_collX->setVisible(true);
        ui->lineEdit_collY->setVisible(true);
    }

}

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function choose_report() {

    var tipo = document.getElementById("formContent:tipo_input");
    var texto = tipo.options[tipo.selectedIndex].text;

    var clase = document.getElementById('formContent:clase_input');
    var value = clase.options[clase.selectedIndex].value;
    document.getElementById('formContent:reportContent').setAttribute("style", "display:none");
    if (texto === 'NORMAL') {
        switch (value) {
            case '1' :
                document.getElementById('formContent:pnlIvaGeneral-n').setAttribute("style", "display:block");
                document.getElementById('formContent:pnlIvaPeqCon-n').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIsrAnual-n').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIso-n').setAttribute("style", "display:none");

                document.getElementById('formContent:pnlIvaGeneral').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIvaPeqCon').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIsrAnual').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIso').setAttribute("style", "display:none");

                break;

            case '2' :
                document.getElementById('formContent:pnlIvaPeqCon-n').setAttribute("style", "display:block");
                document.getElementById('formContent:pnlIvaGeneral-n').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIsrAnual-n').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIso-n').setAttribute("style", "display:none");

                document.getElementById('formContent:pnlIvaGeneral').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIvaPeqCon').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIsrAnual').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIso').setAttribute("style", "display:none");

                break;

            case '3' :
                document.getElementById('formContent:pnlIsrAnual-n').setAttribute("style", "display:block");
                document.getElementById('formContent:pnlIvaGeneral-n').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIvaPeqCon-n').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIso-n').setAttribute("style", "display:none");

                document.getElementById('formContent:pnlIvaGeneral').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIvaPeqCon').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIsrAnual').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIso').setAttribute("style", "display:none");
                break;

            case '4' :
                document.getElementById('formContent:pnlIso-n').setAttribute("style", "display:block");
                document.getElementById('formContent:pnlIvaGeneral-n').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIvaPeqCon-n').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIsrAnual-n').setAttribute("style", "display:none");

                document.getElementById('formContent:pnlIvaGeneral').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIvaPeqCon').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIsrAnual').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIso').setAttribute("style", "display:none");
                break;
        }
    } else if (texto === 'ESPECIAL') {
        switch (value) {
            case '1' :
                document.getElementById('formContent:pnlIvaGeneral').setAttribute("style", "display:block");
                document.getElementById('formContent:pnlIvaPeqCon').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIsrAnual').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIso').setAttribute("style", "display:none");

                document.getElementById('formContent:pnlIvaGeneral-n').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIvaPeqCon-n').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIsrAnual-n').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIso-n').setAttribute("style", "display:none");

                break;

            case '2' :
                document.getElementById('formContent:pnlIvaPeqCon').setAttribute("style", "display:block");
                document.getElementById('formContent:pnlIvaGeneral').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIsrAnual').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIso').setAttribute("style", "display:none");

                document.getElementById('formContent:pnlIvaGeneral-n').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIvaPeqCon-n').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIsrAnual-n').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIso-n').setAttribute("style", "display:none");

                break;

            case '3' :
                document.getElementById('formContent:pnlIsrAnual').setAttribute("style", "display:block");
                document.getElementById('formContent:pnlIvaGeneral').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIvaPeqCon').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIso').setAttribute("style", "display:none");

                document.getElementById('formContent:pnlIvaGeneral-n').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIvaPeqCon-n').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIsrAnual-n').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIso-n').setAttribute("style", "display:none");
                break;

            case '4' :
                document.getElementById('formContent:pnlIso').setAttribute("style", "display:block");
                document.getElementById('formContent:pnlIvaGeneral').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIvaPeqCon').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIsrAnual').setAttribute("style", "display:none");

                document.getElementById('formContent:pnlIvaGeneral-n').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIvaPeqCon-n').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIsrAnual-n').setAttribute("style", "display:none");
                document.getElementById('formContent:pnlIso-n').setAttribute("style", "display:none");
                break;
        }
    }

}


      
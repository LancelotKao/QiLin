SUMMARY = "Intel® CSME Tool"
DESCRIPTION = "Intel® Converged Security and Management Engine Version Detection Tool (Intel® CSMEVDT)"

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "https://downloadmirror.intel.com/28632/CSME_Version_Detection_Tool_Linux.tar.gz"
SRC_URI[sha256sum] = "694d857ff7452424f4d0d4828df83435b176ad0601cf526831edfcd10b9b36fe"

#S = "${WORKDIR}/build"

do_install () {
    install -d ${D}/${sbindir}/CSME/
    cp -rf ${WORKDIR}/sources-unpack ${D}/${sbindir}/CSME
#    cp -rf ${WORKDIR}/intel_csme_version_detection_tool ${D}/${sbindir}/CSME/
#    cp -rf ${WORKDIR}/common ${D}/${sbindir}/CSME/
#    cp -rf ${WORKDIR}/documents ${D}/${sbindir}/CSME/
#    cp -rf ${WORKDIR}/fmt ${D}/${sbindir}/CSME/
#    cp -rf ${WORKDIR}/mei ${D}/${sbindir}/CSME/
#    cp -rf ${WORKDIR}/sps ${D}/${sbindir}/CSME/
}
FILES:${PN} += "${sbindir}/CSME/"

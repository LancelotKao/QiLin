# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Infineon Embedded Linux TPM Toolbox 2 for TPM 2.0"
HOMEPAGE = "https://github.com/Infineon/eltt2"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://License.txt;md5=1e492cfcb05c60002d4bee800bd9c296"

SRC_URI = "git://github.com/Infineon/eltt2;protocol=https;branch=master"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "3d55476179da9bd61c2df1ba1ef010afe27e7776"

# This will prevent the debug files from being split into a separate package, which should avoid the QA issue.
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

S = "${WORKDIR}/git"

do_compile () {
	oe_runmake
}

do_install () {
    install -d ${D}${sbindir}
    install -m 0755 ${S}/eltt2 ${D}${sbindir}/
}

FILES:${PN} += "${sbindir}/eltt2"

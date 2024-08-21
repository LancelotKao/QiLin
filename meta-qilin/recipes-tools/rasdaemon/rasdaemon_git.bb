# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   COPYING
#LICENSE = "Unknown"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=d3070efe0afa3dc41608bd82c00bb0dc"

SRC_URI = "git://github.com/mchehab/rasdaemon;protocol=https;branch=master"

# Modify these as desired
PV = "0.8.0+git${SRCPV}"
SRCREV = "a247baf7110ab6427259eb1421a103e2021a8735"
#SRCREV = "4e83b848e7961af25028f3a2cecf37a63279a2bf"

S = "${WORKDIR}/git"

# NOTE: unable to map the following pkg-config dependencies: libtraceevent
#       (this is based on recipes that have previously been built and packaged)
DEPENDS = "libtraceevent systemd"
RDEPENDS:${PN} += " \
                    libsystemd \
                    perl \
                    perl-module-file-basename \
                    perl-module-file-find \
                    perl-module-file-spec \
                    perl-module-file-glob \
                    perl-module-constant \
                    perl-module-getopt-long \
                    perl-module-posix \ 
                    perl-module-carp \
"

# NOTE: if this software is not capable of being built in a separate build directory
# from the source, you should replace autotools with autotools-brokensep in the
# inherit line
inherit pkgconfig autotools systemd

# Specify any options you want to pass to the configure script using EXTRA_OECONF:
EXTRA_OECONF = ""

do_install:append () {
    install -d ${D}/${sbindir}
    install -m 0755 ${WORKDIR}/build/rasdaemon ${D}/${sbindir}/
    install -m 0755 ${WORKDIR}/build/util/ras-mc-ctl ${D}/${sbindir}/
    install -d ${D}/${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/build/misc/rasdaemon.service ${D}/${systemd_system_unitdir}/
    install -m 0644 ${WORKDIR}/build/misc/ras-mc-ctl.service ${D}/${systemd_system_unitdir}/
}

FILES:${PN} += "${sbindir} ${systemd_system_unitdir}"

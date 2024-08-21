FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

#INSANE_SKIP:${PN} += "already-stripped"

SRC_URI:append = " \
    file://bashrc.sh \
    file://vimrc.sh \
"

do_install:append () {
    install -d ${D}${sysconfdir}/skel
    install -m 644 ${S}/bashrc.sh ${D}${sysconfdir}/skel/.bashrc
    install -m 644 ${S}/bashrc.sh ${D}/home/root/.bashrc
    install -m 644 ${S}/vimrc.sh ${D}${sysconfdir}/skel/.vimrc
    install -m 644 ${S}/vimrc.sh ${D}/home/root//.vimrc
}

FILES:${PN} += " \
                ${sysconfdir}/skel/.bashrc \
                ${sysconfdir}/skel/.vimrc \
                /home/root/.bashrc \
                /home/root/.vimrc \
"

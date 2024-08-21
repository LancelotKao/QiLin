SRC_URI += "file://banner"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
FILES:${PN} += "/etc/ssh/banner"

do_install:append () {
  install -m 0444 ${WORKDIR}/banner ${D}${sysconfdir}/ssh/banner

  sed -i -e 's:#Banner.*:Banner /etc/ssh/banner:' ${D}${sysconfdir}/ssh/sshd_config
}


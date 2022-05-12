<?php

namespace App\Entity;

use App\Repository\PanierRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=PanierRepository::class)
 */
class Panier
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id_panier;

    /**
     * @ORM\ManyToOne(targetEntity=User::class, inversedBy="paniers")
     * @ORM\JoinColumn(name="id_user",referencedColumnName="id_user",nullable=false)
     */
    private $id_user;

    /**
     * @ORM\OneToMany(targetEntity=Produit::class, mappedBy="panier", orphanRemoval=true)
     */
    private $refProd;

    /**
     * @ORM\Column(type="integer")
     */
    private $quantite;

    /**
     * @ORM\Column(type="float")
     */
    private $totalPanier;

    public function __construct()
    {
        $this->refProd = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getIdPanier(): ?int
    {
        return $this->id_panier;
    }

    public function setIdPanier(int $id_panier): self
    {
        $this->id_panier = $id_panier;

        return $this;
    }

    public function getIdUser(): ?user
    {
        return $this->id_user;
    }

    public function setIdUser(?user $id_user): self
    {
        $this->id_user = $id_user;

        return $this;
    }

    /**
     * @return Collection<int, produit>
     */
    public function getRefProd(): Collection
    {
        return $this->refProd;
    }

    public function addRefProd(produit $refProd): self
    {
        if (!$this->refProd->contains($refProd)) {
            $this->refProd[] = $refProd;
            $refProd->setPanier($this);
        }

        return $this;
    }

    public function removeRefProd(produit $refProd): self
    {
        if ($this->refProd->removeElement($refProd)) {
            // set the owning side to null (unless already changed)
            if ($refProd->getPanier() === $this) {
                $refProd->setPanier(null);
            }
        }

        return $this;
    }

    public function getQuantite(): ?int
    {
        return $this->quantite;
    }

    public function setQuantite(int $quantite): self
    {
        $this->quantite = $quantite;

        return $this;
    }

    public function getTotalPanier(): ?float
    {
        return $this->totalPanier;
    }

    public function setTotalPanier(float $totalPanier): self
    {
        $this->totalPanier = $totalPanier;

        return $this;
    }
}
